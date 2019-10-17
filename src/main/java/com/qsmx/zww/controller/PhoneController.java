package com.qsmx.zww.controller;


import com.qsmx.zww.servcie.PhoneServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "Phone")
public class PhoneController {

    @Autowired
    private PhoneServer phoneServer;

    @RequestMapping(value = "/get")
    public String getphoneInfometion() {
        phoneServer.getPhone();
        return "完成";
    }
}
