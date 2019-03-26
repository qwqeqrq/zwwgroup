package com.qsmx.zww.servcie;

import com.qsmx.zww.uitil.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zww on 2019-03-26. 获取B站 热门
 */
@Service
public class BilibiliServiceImpl implements BilibiliService {

    /**
     * @描述： 获取B站 热门视频排行
     * @参数： []
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-03-26
     * @修改人和其它信息：
     */
    @Override
    public String RankListByBilibili() {
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
}
