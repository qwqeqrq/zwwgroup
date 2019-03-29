package com.qsmx.zww.controller;


import com.qsmx.zww.mapper.BilibiliMapper;
import com.qsmx.zww.servcie.BilibiliService;
import com.qsmx.zww.uitil.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-03-26.
 */
@RestController
@RequestMapping(value = "bilibili")
public class BilibiliController {

    @Autowired
    private BilibiliService bilibiliService;

    @Autowired
    private BilibiliMapper bilibiliMapper;

    @RequestMapping(value = "rank")
    public String getRankVidio() {
        try {
            String jsonStr = bilibiliService.rankListByBilibili();
            Map map1 = JsonUtil.aliToBean(jsonStr, Map.class);
            List<Map> lists = JsonUtil.aliToBean(map1.get("rankList").toString(), List.class);
            for (int i = 0; i < lists.size(); i++) {
                Map map = lists.get(i);
                System.out.println("-----title----" + map.get("title"));
                map.put("url", "https://www.bilibili.com/video/av" + map.get("aid") + "/");
                map.put("pictureUrl", map.get("pic"));
                System.out.println("-----aid----" + map.get("aid"));
                System.out.println("-----url----" + "https://www.bilibili.com/video/av" + map.get("aid") + "/");
                if (bilibiliMapper.getBilibiliInfo(map) == null) {
                    int o = bilibiliMapper.insertBilibili(map);
                    if (o > 0) {
                        System.out.println("哔哩哔哩成功！");
                    }
                } else {
                    System.out.println("-------已存在,下一个");
                }
            }
            return "BIlibili";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }

    //电视
    @RequestMapping(value = "doubanTV")
    public String douBanTv() {
        return bilibiliService.douBanTV();
    }

    //电影
    @RequestMapping(value = "doubanMovie")
    public String douBanMocie() {
        return bilibiliService.douBanMovie();
    }
}
