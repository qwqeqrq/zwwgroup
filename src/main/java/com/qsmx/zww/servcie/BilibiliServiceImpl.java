package com.qsmx.zww.servcie;

import com.qsmx.zww.mapper.CarMapper;
import com.qsmx.zww.mapper.DouBanTVmapper;
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
    private DouBanTVmapper douBanTVmapper;
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
            headers.put("Cookie", "UM_distinctid=167626cee4d377-0905c83b97977b-3c604504-1fa400-167626cee4e647; buvid3=609D678B-726F-457D-BF21-30321BECC35A85473infoc; _uuid=FB77FF41-DF53-2157-C962-78DAA03F5FAC13925infoc; LIVE_BUVID=AUTO8915535639133294; stardustvideo=1; CURRENT_FNVAL=16; sid=5tvqql0c; fts=1553565311");
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
