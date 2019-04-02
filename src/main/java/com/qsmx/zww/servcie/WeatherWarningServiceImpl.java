package com.qsmx.zww.servcie;

import com.qsmx.zww.mapper.WeatherWarningMapper;
import com.qsmx.zww.uitil.HttpClient;
import com.qsmx.zww.uitil.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-04-02. 气象预警信息
 */
@Service
public class WeatherWarningServiceImpl implements WeatherWarningService {

    private WeatherWarningMapper weatherWarningMapper;

    @Autowired
    public WeatherWarningServiceImpl(WeatherWarningMapper weatherWarningMapper) {
        this.weatherWarningMapper = weatherWarningMapper;
    }

    @Override
    public String getWeatherWarning() {
        try {
            String url = "http://www.12379.cn/data/alarm_list_all.html";
            String response = HttpClient.doGet(url);
            Map map = JsonUtil.aliToMap(response);
            if (map != null && !map.isEmpty()) {
                List<Map> list = JsonUtil.aliToList(map.get("alertData").toString());
                if (!list.isEmpty()) {
                    list.stream().forEach((date) -> {
                        List<Map> mapList = weatherWarningMapper.selectWeatherWarning(date);
                        if (mapList != null && mapList.size() != 0) {
                            System.out.println("该信息已存在，正在采集下一个");
                        }else {
                            weatherWarningMapper.insertWeatherWarning(date);
                        }
                    });
                }
                return "更新成功";
            } else {
                return "数据为空，请检查URL";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "更新数据失败";
    }
}
