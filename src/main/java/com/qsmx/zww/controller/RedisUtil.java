package com.qsmx.zww.controller;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    //Redis 服务器   IP
    private String address = "127.0.0.1";

    //Redis的端口号
    private int port = 6379;

    //访问密码
    private String password = "";

    //连接Redis等待时间
    private int timeOut = 10000;

    //可用连接实例的最大数目，默认值为8
    //如果赋值为-1，则表示不限制;如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    private int maxTotal = 1024;

    //控制一个pool最多有多少个状态为Idle(空闲的)的jedis实例，默认值也是8
    private int maxIdle = 200;

    //等待可用连接的最大时间,单位为毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private int maxWait = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
    private boolean testOnBorrow = true;

    //连接池
    private JedisPool jedisPool = null;

    //构造函数

    public RedisUtil() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            //有密码时候获取连接池
            //  jedisPool = new JedisPool(config, address, port, timeOut, password);

            //无密码直接获取连接池 
            //jedisPool = new JedisPool(address, port);//默认连接db0
            jedisPool = new JedisPool(new GenericObjectPoolConfig(), address, port, 2000, (String) null, 3, (String) null);//修改为3
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取Jedis实例
    public Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        return null;
    }


}
