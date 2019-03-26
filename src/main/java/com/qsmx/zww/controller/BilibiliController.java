package com.qsmx.zww.controller;

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
    BilibiliService bilibiliService;

    @RequestMapping(value = "rank")
    public String getRankVidio() {
        String jsonStr = bilibiliService.RankListByBilibili();
        Map map1 = JsonUtil.aliToBean(jsonStr, Map.class);
        List<Map> lists = JsonUtil.aliToBean(map1.get("rankList").toString(), List.class);
        for (int i = 0; i < 10; i++) {
            Map map = lists.get(i);
            System.out.println("-----title----" + map.get("title"));
            System.out.println("-----aid----" + map.get("aid"));
            System.out.println("-----url----" + "https://www.bilibili.com/video/av" + map.get("aid") + "/");
        }
        return "BIlibili";
    }
}
