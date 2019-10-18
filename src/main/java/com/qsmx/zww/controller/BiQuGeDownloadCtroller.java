package com.qsmx.zww.controller;

import com.qsmx.zww.servcie.BiQuGeDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "biquge")
public class BiQuGeDownloadCtroller {

    @Autowired
    private BiQuGeDownloadService biQuGeDownloadService;

    @RequestMapping(value = "download")
    public String dowmLoad() {
        biQuGeDownloadService.downLoad();
        return "下载成功";
    }

    @RequestMapping(value = "downloadGBK")
    public String downGBK() {
        biQuGeDownloadService.downGBK();
        return "下载成功";
    }
}
