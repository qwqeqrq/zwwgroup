package com.qsmx.zww.controller;

import com.qsmx.zww.servcie.PoetryService;
import com.qsmx.zww.servcie.SurnameService;
import com.qsmx.zww.uitil.DingDingMessageBeanUiti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * Created by zww on 2019-03-05.
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private SurnameService surnameService;
    @Autowired
    private PoetryService poetryService;
    @Autowired
    private DingDingMessageBeanUiti dingDingMessageBeanUiti;
    @Autowired
    private KafkaTemplate kafkaTemplate;


    @RequestMapping(value = "shijing")
    public String getController() {
        return "有美一人，清扬婉兮，邂逅相遇，适我愿兮。";
    }

    @RequestMapping(value = "surname")
    public List<Map<String, Object>> getSurname() {
        return surnameService.getSurname();
    }

    @RequestMapping(value = "sendMessage")
    public String sendMessage() {
        try {
            kafkaTemplate.send("test", "我是java生产者");
            System.out.println("--------------已生产");
          /*  List<String> s = new ArrayList<>();
            s.add("18829288843");
            s.add("18729086497");
            dingDingMessageBeanUiti.sendMessageUtil(false, "我是机器人：王侯将相宁有种乎？", s);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("run")
    public String run() {
        Integer result = poetryService.insertStealPoetry();
        //Integer result1 = poetryService.insertNetName();
        if (result == null /* result1 == null*/) {
            System.out.println("程序完毕！！！！");
            return "完毕";
        }
        return "runing";
    }
}
