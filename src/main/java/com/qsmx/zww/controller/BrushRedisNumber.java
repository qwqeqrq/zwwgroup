package com.qsmx.zww.controller;


import com.alibaba.fastjson.JSON;
import com.qsmx.zww.mapper.CarMapper;
import com.qsmx.zww.uitil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping(value = "/c")
public class BrushRedisNumber {

    /**
     * 刷redis 验证扩大1000倍 备库 代码 保留 没准什么时候还要用
     * 1.需要配置  数据库地址 和 redis 拉取地址
     * 2.备份本地redis地址 这个
     */

    @Autowired
    private RedisTemplate redisTemplate;//操作测试redis

    @Autowired
    private CarMapper carMapper;

    private RedisUtil redisUtil = new RedisUtil();

    //测试redis
    @RequestMapping(value = "/testRedis")
    public String testRedis() {
        String clickCnt = Optional.ofNullable(redisTemplate.opsForValue().get("request")).orElse("").toString();
        if (!StringUtils.isEmpty(clickCnt) && Long.parseLong(clickCnt) > 5) {
            System.out.println("超过5次了");
            redisTemplate.expire("request", 3600 * 24, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().increment("request", 1);
            redisTemplate.expire("request", 7200, TimeUnit.SECONDS);
            System.out.println("加了一次，请查看");
        }
        return "完成";
    }


    //对比接口
    @RequestMapping(value = "/d")
    public String get() {
        try {
            long startTime = System.currentTimeMillis();
            List<String> AdGroups = carMapper.getAdGroupid();
            StringBuffer stringBuffer = new StringBuffer();
            Jedis jedis = redisUtil.getJedis();//操作本地redis
            AtomicInteger num = new AtomicInteger();
            AtomicInteger aderrnum = new AtomicInteger();//广告组异常数量
            AtomicInteger cuerrnum = new AtomicInteger();//客户异常数量
            AdGroups.stream().forEach(e -> {
                Map<String, String> testMap = redisTemplate.opsForHash().entries("adgroup-yd:" + e);//去测试的读
                Map map = jedis.hgetAll("adgroup-yd:" + e);//读本地
                if (map != null && map.size() != 0) {
                    int flag = 0;
                    num.getAndIncrement();
                    Long localLong = Long.parseLong(Optional.ofNullable(map.get("beforeToday")).orElse("0").toString());//本地 beforeToday long
                    Long localtodayLong = Long.parseLong(Optional.ofNullable(map.get("today")).orElse("0").toString());//本地 today long
                    //对比adgroup本地beforeToday
                    if (!testMap.get("beforeToday").equals(String.valueOf(localLong * 1000))) {
                        flag++;
                        System.out.println("=============beforeToday===adgroup-yd:" + e + "false================");
                    }
                    //对比adgroup本地today
                    if (!(String.valueOf(localtodayLong * 1000)).equals(testMap.get("today"))) {
                        flag++;
                        System.out.println("=============Today==adgroup-yd:" + e + "false================");
                    }
                    if (flag != 0) {//返回异常信息
                        aderrnum.getAndIncrement();
                        stringBuffer.append("adgroup-yd:" + e + "备份redis" + JSON.toJSONString(map));
                        stringBuffer.append("adgroup-yd:" + e + "测试redis" + JSON.toJSONString(testMap));
                    }
                }
            });

       /*     List<String> custs = carMapper.getCustid();
            custs.stream().forEach(e -> {
                Map<String, String> custMap = redisTemplate.opsForHash().entries("customer:" + e);//去测试的读
                Map localcustomer = jedis.hgetAll("customer:" + e);//本地读
                if (localcustomer != null && localcustomer.size() != 0) {
                    int flag = 0;
                    num.getAndIncrement();
                    Long localbeforeTodayLong = Long.parseLong(Optional.ofNullable(localcustomer.get("beforeToday")).orElse("0").toString());//本地 beforeToday long
                    Long localtodayLong = Long.parseLong(Optional.ofNullable(localcustomer.get("today")).orElse("0").toString());//本地 today long
                    if (!custMap.get("beforeToday").equals(String.valueOf(localbeforeTodayLong * 1000))) {
                        flag++;
                        System.out.println("=============beforeToday==customer:" + e + "false================");
                    }
                    if (!custMap.get("today").equals(String.valueOf(localtodayLong * 1000))) {
                        flag++;
                        System.out.println("=============beforeToday===customer:" + e + "false================");
                    }
                    if (flag != 0) {//返回异常信息
                        cuerrnum.getAndIncrement();
                        stringBuffer.append("customer:" + e + "备份redis" + JSON.toJSONString(localcustomer));
                        stringBuffer.append("customer:" + e + "测试redis" + JSON.toJSONString(custMap));
                    }
                }
            });*/
            long endTime = System.currentTimeMillis();
            System.out.println("总共比对了:" + num + "个=====广告组异常数量:" + aderrnum + "=====客户异常数量:" + cuerrnum);
            System.out.println("总共耗时" + ((endTime - startTime) / 1000 / 60) + "分钟");
            return stringBuffer.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //同步备份redis
    @RequestMapping(value = "/r")
    private String readWriteRedis() {
        try {
            long startTime = System.currentTimeMillis();
            Jedis jedis = redisUtil.getJedis();//操作本地redis
            AtomicInteger n = new AtomicInteger();
            List<String> AdGroups = carMapper.getAdGroupid();
            AdGroups.stream().forEach(e -> {
                Map<String, String> resultMap = redisTemplate.opsForHash().entries("adgroup-yd:" + e);
                if (resultMap != null && resultMap.size() != 0) {
                    n.getAndIncrement();
                    jedis.hmset("adgroup-yd:" + e, resultMap);
                }
                System.out.println("正在同步====》");
            });
            //同步客户
           /* List<String> custs = carMapper.getCustid();
            custs.stream().forEach(e -> {
                Map<String, String> resultMap = redisTemplate.opsForHash().entries("customer:" + e);
                if (resultMap != null && resultMap.size() != 0) {
                    jedis.hmset("customer:" + e, resultMap);
                    n.getAndIncrement();
                }
                System.out.println("customer");
            });*/
            long endTime = System.currentTimeMillis();
            System.out.println("总共耗时" + ((endTime - startTime) / 1000 / 60) + "分钟");
            return "同步redis success总共耗时" + ((endTime - startTime) / 1000 / 60) + "分钟===" + n + "条数据";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "同步redis失败";
    }


    // 字符串操作

    public void testStr() {
        Jedis jedis = redisUtil.getJedis();
        jedis.set("id", "15"); // 只能是字符串
        String id = jedis.get("id");
        System.out.println(id);
        jedis.close();
    }

    // 操作 map

    public void testMap() {
        Jedis jedis = redisUtil.getJedis();
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);
        jedis.hdel("user", "age");
        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
        jedis.close();
    }

    // 操作 list

    public void testList() {
        Jedis jedis = redisUtil.getJedis();
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));
        jedis.del("java framework");
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");
        jedis.rpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));
        jedis.close();
    }

    // 操作 set

    public void testSet() {
        Jedis jedis = redisUtil.getJedis();
        jedis.sadd("user1", "liuling");
        jedis.sadd("user1", "xinxin");
        jedis.sadd("user1", "ling");
        jedis.sadd("user1", "zhangxinxin");
        jedis.sadd("user1", "who");
        jedis.srem("user1", "who"); // 移除noname
        System.out.println(jedis.smembers("user1"));// 获取所有加入的value
        System.out.println(jedis.sismember("user1", "who"));// 判断 who
        System.out.println(jedis.srandmember("user1")); // 是否是user集合的元素
        System.out.println(jedis.scard("user1"));// 返回集合的元素个数
        jedis.close();
    }

    // jedis 排序

    public void testOrder() {
       /* Jedis jedis = redisUtil.getJedis();
        jedis.del("a");
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));
        System.out.println(jedis.sort("a"));
        System.out.println(jedis.lrange("a", 0, -1));*/
        for (int i = (int) ('a'); i < (int) ('z'); i++) {
            System.out.println(i);
            int y = 65;
            System.out.println((char) (y + 2));
        }
        /* jedis.close();*/
    }

    @RequestMapping(value = "/y")
    public String rere(HttpServletRequest request) {
        System.out.println(JSON.toJSONString(request.getParameterMap()));
        return request.toString();
    }

    @RequestMapping(value = "/pipeline")
    public String pipeline(HttpServletRequest request) throws InterruptedException {
        List<String> list = new ArrayList();
        for (int i = 0; i < 30; i = i + 2) {
            list.add("key:" + i);
            //   redisTemplate.opsForValue().set("key:" + String.valueOf(i), "value:" + String.valueOf(i));
        }
        // System.out.println("写入成功，速去查看");
        List list1 = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (String key : list) {
                    redisConnection.get(redisTemplate.getStringSerializer().serialize(key));
                }
                return null;
            }
        });
        List list2 = redisTemplate.opsForValue().multiGet(list);
        for (Object o : list2) {
            if (o != null)
                System.out.println(o);
        }
        System.out.println("seccess");
        return "seccess";
    }

}
