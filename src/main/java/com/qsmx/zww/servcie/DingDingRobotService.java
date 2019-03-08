package com.qsmx.zww.servcie;

import com.qsmx.zww.po.DingDingMessageInfo;

/**
 * Created by zww on 2019-03-08  钉钉机器人
 */
public interface DingDingRobotService {

    /**
     * @描述： 钉钉机器人发消息
     * @参数：
     * @返回值：
     * @创建人： zhangww
     * @创建时间： 2019-03-08
     * @修改人和其它信息：
     */
    Boolean sendMessageByDingDingRobot(DingDingMessageInfo dingDingMessageInfo,String url);
}
