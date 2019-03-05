package com.qsmx.zww.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zww on 2019-03-05.
 */
@RestController
@RequestMapping(value = "test")
public class TestController {
    @RequestMapping(value = "shijing")
    public String getController() {
        return "有美一人，清扬婉兮，邂逅相遇，适我愿兮。";
    }
}
