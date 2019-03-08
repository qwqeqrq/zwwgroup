package com.qsmx.zww.controller;

import com.qsmx.zww.servcie.SurnameService;
import com.qsmx.zww.uitil.DingDingMessageBeanUiti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
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
    private DingDingMessageBeanUiti dingDingMessageBeanUiti;


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
           List<String> s = new ArrayList<>();
           dingDingMessageBeanUiti.sendMessageUtil(false, "呵呵哒", s);
       }catch (Exception e){
           e.printStackTrace();
       }
        return "";
    }
}
