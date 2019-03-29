package com.qsmx.zww.servcie;

import com.qsmx.zww.mapper.NetNameMapper;
import com.qsmx.zww.mapper.PoetryMapper;
import com.qsmx.zww.po.NetNameInfo;
import com.qsmx.zww.po.PoetryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-03-25.
 */
@Service
public class PoetryServiceImpl implements PoetryService {

    @Autowired
    private PoetryMapper poetryMapper;
    @Autowired
    private NetNameMapper netNameMapper;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @描述： 疯狂访问随机古诗接口，刷取数据
     * @参数： [poetry]
     * @返回值： java.lang.Integer
     * @创建人： zhangww
     * @创建时间： 2019-03-25
     * @修改人和其它信息：
     */
    @Override
    public Integer insertStealPoetry() {
        try {
            int i = 0;
            int j = 0;
            while (true) {
                Map map = restTemplate.getForEntity("https://api.apiopen.top/recommendPoetry", Map.class).getBody();
                if (200 == (int) map.get("code")) {
                    PoetryInfo poetryInfo = new PoetryInfo();
                    poetryInfo.setAuthor(((Map) map.get("result")).get("authors").toString());
                    poetryInfo.setName(((Map) map.get("result")).get("title").toString());
                    String verse = ((Map) map.get("result")).get("content").toString().replace("|", "");
                    poetryInfo.setVerse(verse);
                    //查询有没有
                    PoetryInfo poetryInfo1 = poetryMapper.selectPoetryByName(poetryInfo);
                    if (poetryInfo1 == null) {
                        Integer result = poetryMapper.insertPoetry(poetryInfo);
                        if (result > 0) {
                            System.out.println("-------成功偷到一首古诗------" + i);
                            i++;
                            j=0;
                        }
                    } else {
                        if (j > 20) {
                            break;
                        }
                        System.out.println(poetryInfo1.getName() + "--------------已经有了,正在读取下一个");
                        j++;
                    }
                } else {
                    System.out.println("网站拒绝访问");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            insertStealPoetry();
        }
        return null;
    }

    /**
     * @描述： 偷网名
     * @参数： []
     * @返回值： java.lang.Integer
     * @创建人： zhangww
     * @创建时间： 2019-03-25
     * @修改人和其它信息：
     */
    @Override
    public Integer insertNetName() {
        try {
            int i = 1;
            int j = 1;
            a:
            while (true) {
                String url = "https://www.apiopen.top/femaleNameApi?page=" + String.valueOf(i);
                Map map = restTemplate.getForEntity(url, Map.class).getBody();
                if (200 == Integer.valueOf(map.get("code").toString())) {
                    i++;
                    NetNameInfo netNameInfo = new NetNameInfo();
                    List<Map> list = ((List) map.get("data"));
                    if (list == null || list.isEmpty()) {
                        System.out.println("已经被你薅完了");
                        System.out.println(url+"---------"+i);
                        i++;
                        continue;
                    }
                    for (Map li : list) {
                        netNameInfo.setName(li.get("femalename").toString());
                        Map map1 = netNameMapper.selectNetName(netNameInfo);
                        if (map1 == null) {
                            int n = netNameMapper.insertName(netNameInfo);
                            if (n > 0) {
                                System.out.println("成功获取一个网名");
                            }
                        } else {
                            if (j == 5) {
                                i++;
                                break;
                            }
                            j++;
                            System.out.println("以获取到，正在搜索下一个");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
