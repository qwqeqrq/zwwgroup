package com.qsmx.zww.uitil;

import com.qsmx.zww.po.DingDingAtInfo;
import com.qsmx.zww.po.DingDingMessageInfo;
import com.qsmx.zww.servcie.DingDingRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-03-08. 钉钉消息工具类
 */
@Component
public class DingDingMessageBeanUiti {

    @Value("${dingDingRobotUrl}")
    private String dingDingRobotUrl;


    @Autowired
    private DingDingRobotService robotService;

    /**
     * @描述： 钉钉机器人发送消息体
     * @参数： [atList 需要被@的电话 , atAll 是否@所有人 ,message 发送消息内容]
     * @返回值： com.qsmx.zww.po.DingDingMessageInfo
     * @创建人： zhangww
     * @创建时间： 2019-03-08
     * @修改人和其它信息：
     */
    //得到消息发送体
    public static DingDingMessageInfo getDingDingMessageBean(List<String> atList, boolean atAll, String message) {
        try {
            DingDingMessageInfo dingDingMessageInfo = new DingDingMessageInfo();//消息体BEAN
            DingDingAtInfo dingDingAtInfo = new DingDingAtInfo();//设置At 对象
            List<String> list = new ArrayList<>();//需要被@的联系人
            list.addAll(atList);
            dingDingAtInfo.setAtMobiles(list);
            if (atAll) {
                dingDingAtInfo.setAtAll(atAll);
            }
            Map<String, String> messageMessage = new HashMap<>();
            messageMessage.put("content", message);
            dingDingMessageInfo.setMsgtype("text");
            dingDingMessageInfo.setText(messageMessage);
            dingDingMessageInfo.setAt(dingDingAtInfo);
            return dingDingMessageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @描述： 通过钉钉机器人给群里发起信息
     * @参数： [boolean atAll 是否@所有人, String message 发送消息内容] List<String> atList 需要被@的手机列表
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-03-08
     * @修改人和其它信息：
     */
    public  Boolean sendMessageUtil(boolean atAll, String message, List<String> atList) throws Exception {
        DingDingMessageInfo dingDingMessageInfo = DingDingMessageBeanUiti.getDingDingMessageBean(atList, false, message);
        if (dingDingMessageInfo == null) {
            return false;
        }
        if (robotService.sendMessageByDingDingRobot(dingDingMessageInfo, dingDingRobotUrl)) {
            return true;
        }
        return false;
    }
}
