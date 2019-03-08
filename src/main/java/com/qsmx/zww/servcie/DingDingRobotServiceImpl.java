package com.qsmx.zww.servcie;

import com.qsmx.zww.po.DingDingMessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

/**
 * Created by zww on 2019-03-08.钉钉机器人发送消息
 */
@Service
public class DingDingRobotServiceImpl implements DingDingRobotService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @描述： 钉钉机器人发送消息方法
     * @参数： [dingDingMessageInfo 参数类, url 发送的群的连接地址]
     * @返回值： java.lang.Boolean
     * @创建人： zhangww
     * @创建时间： 2019-03-08
     * @修改人和其它信息：
     */
    @Override
    public Boolean sendMessageByDingDingRobot(@RequestBody DingDingMessageInfo dingDingMessageInfo, String url) {
        try {
            Map<String, Object> resultMap = restTemplate.postForObject(url, dingDingMessageInfo, Map.class);
            if ("ok".equals(Optional.ofNullable(resultMap.get("errmsg")).orElse("false").toString()) &&
                    0 == Integer.valueOf(Optional.ofNullable(resultMap.get("errcode")).orElse("1001").toString())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

