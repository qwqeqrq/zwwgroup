package com.qsmx.zww.controller;

import com.qsmx.zww.servcie.WeatherWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zww on 2019-04-02.
 */
@RestController
@RequestMapping(value = "warning")
public class WeatherWarningController {

    private WeatherWarningService weatherWarningService;

    @Autowired
    public WeatherWarningController(WeatherWarningService weatherWarningService) {
        this.weatherWarningService = weatherWarningService;
    }


    @RequestMapping(value = "coming")
    public String collection() {
        return weatherWarningService.getWeatherWarning();
    }
}
