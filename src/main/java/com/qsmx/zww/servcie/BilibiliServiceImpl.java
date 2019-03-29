package com.qsmx.zww.servcie;

import com.qsmx.zww.mapper.CarMapper;
import com.qsmx.zww.mapper.DouBanTVMapper;
import com.qsmx.zww.uitil.HttpClient;
import com.qsmx.zww.uitil.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-03-26. 获取B站 热门
 */
@Service
public class BilibiliServiceImpl implements BilibiliService {


    @Autowired
    private DouBanTVMapper douBanTVmapper;
    @Autowired
    private CarMapper carMapper;

    /**
     * @描述： 获取B站 热门视频排行
     * @参数： []
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-03-26
     * @修改人和其它信息：
     */
    @Override
    public String rankListByBilibili() {
        try {
            Map<String, String> headers = new HashMap();
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9");
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            headers.put("Upgrade-Insecure-Requests", "1");
            headers.put("Cookie", "UM_distinctid=167626cee4d377-0905c83b97977b-3c604504-1fa400-167626cee4e647; buvid3=609D678B-726F-457D-BF21-30321BECC35A85473infoc; " +
                    "_uuid=FB77FF41-DF53-2157-C962-78DAA03F5FAC13925infoc; LIVE_BUVID=AUTO8915535639133294; stardustvideo=1; CURRENT_FNVAL=16; sid=5tvqql0c; fts=1553565311");
            String strHtml = HttpClient.doGet("https://www.bilibili.com/ranking?spm_id_from=333.334.b_62616e6e65725f6c696e6b.1", headers, new HashMap<>());
            System.out.println(strHtml);
            int begin = strHtml.indexOf("{\"rankList\"");
            int end = strHtml.indexOf(";(function()");
            return strHtml.substring(begin, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取哔哩哔哩失败";
    }

    @Override
    public String douBanTV() {
        int i = 0;
        try {
            a:
            while (true) {
                String url = "https://movie.douban.com/j/search_subjects?type=tv&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=" + i;
                String result = HttpClient.doGet(url);
                Map map = JsonUtil.aliToMap(result);
                if (map != null && !map.isEmpty()) {
                    List<Map> lists = JsonUtil.aliToList(map.get("subjects").toString());
                    if (lists.isEmpty()) {
                        break;
                    }
                    for (Map map1 : lists) {
                        if (douBanTVmapper.selectDouBanTV(map1) == null || douBanTVmapper.selectDouBanTV(map1).isEmpty()) {
                            int s = douBanTVmapper.insertDoubanTV(map1);
                            if (s > 0) {
                                System.out.println("成功获得电视剧榜");
                            }
                        } else {
                            System.out.println("已经存在！正在下一个");
                            i++;
                            continue a;
                        }
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "错误";
        }
        return "薅豆瓣电视完成，去看看成功吧";
    }

    @Override
    public String douBanMovie() {
        int i = 0;
        try {
            Map<String, String> headers = new HashMap();
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9");
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            headers.put("Upgrade-Insecure-Requests", "1");
            headers.put("Cookie", "bid=q-Q2oKfrqFc; douban-fav-remind=1; ll=\"108288\"; __yadk_uid=PLXa411F40U4YWiE5JhyAwreJ9wMfD61; " +
                    "_vwo_uuid_v2=D300B74BFD62D58FACDA2879B848E042D|9e64e5a0eb2574222d2ee8bc7ccc498a; gr_user_id=2211f882-cab8-41e4-8b54-c6e0ee53ebd" +
                    "a; ct=y; viewed=\"30338769\"; dbcl2=\"181965636:wrhzirGCK0E\"; ck=0xBe; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1553852862%2C%22http" +
                    "s%3A%2F%2Faccounts.douban.com%2Fpassport%2Flogin%3Fredir%3Dhttps%253A%252F%252Fmovie.douban.com%252Fsubject%252F26733371%252F%22%5D; _pk_se" +
                    "s.100001.4cf6=*; __utmc=30149280; __utmc=223695111; push_noty_num=0; push_doumail_num=0; __utma=30149280.162132527.1541466304.1553852863.155385" +
                    "3060.27; __utmb=30149280.0.10.1553853060; __utmz=30149280.1553853060.27.26.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utma=223695111.2059398818" +
                    ".1541495464.1553852863.1553853060.8; __utmb=223695111.0.10.1553853060; __utmz=223695111.1553853060.8.7.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; _pk" +
                    "_id.100001.4cf6=97a5e425ed7c4e66.1541495464.7.1553853274.1553595030.");
            a:
            while (true) {
                String url = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=" + i;
                String result = HttpClient.doGet(url, headers, new HashMap<>());
                Map map = JsonUtil.aliToMap(result);
                if (map != null && !map.isEmpty()) {
                    List<Map> lists = JsonUtil.aliToList(map.get("subjects").toString());
                    if (lists.isEmpty()) {
                        break;
                    }
                    for (Map map1 : lists) {
                        if (douBanTVmapper.selectDouBanMovie(map1) == null || douBanTVmapper.selectDouBanMovie(map1).isEmpty()) {
                            map1.put("url", map1.get("url").toString().replace("'\'", ""));
                            map1.put("cover", map1.get("cover").toString().replace("'\'", ""));
                            int s = douBanTVmapper.insertDoubanMovie(map1);
                            if (s > 0) {
                                System.out.println("成功获得电影剧榜");
                            }
                        } else {
                            System.out.println("已经存在！正在下一个");
                            i++;
                            continue a;
                        }
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "错误";
        }
        return "薅豆瓣最近电影完成，去看看吧";
    }



    /*public static void main(String[] args) throws Exception {
        //汽车之家 偷取所有汽车品牌
        String result = HttpClient.doGet("https://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=11");
        Map map = JsonUtil.aliToMap(result);
       List<Map> list = JsonUtil.aliToList(JsonUtil.aliToMap(map.get("result").toString()).get("branditems").toString());
       for (Map li :list){
           carMapper.insertCar(li);
       }
    }*/

    /*偷取了全国的大学信息哈哈*/
}
