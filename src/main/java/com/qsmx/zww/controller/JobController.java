package com.qsmx.zww.controller;

import com.qsmx.zww.servcie.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zww on 2019-03-28.获取智联java招聘的信息
 */
@RestController
@RequestMapping(value = "job")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "go")
    public String job() {
        return jobService.job();
    }
}
