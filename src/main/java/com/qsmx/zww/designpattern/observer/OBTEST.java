package com.qsmx.zww.designpattern.observer;

/**
 * Created by zww on 2019-04-19.
 */
public class OBTEST {
    public static void main(String[] args) {
        XS xishi = new XS();//创建一个西施up主
       // DC dc =new DC();//新关注了一个dc，我们希望他也被监视
        xishi.getObservers().add(new YangGuo());
        xishi.getObservers().add(new HanXin());
      //  dc.setObserver(new YangGuo());
        xishi.setMessage("我要去买原子弹！这个你怎么才能检查的到呢");
        xishi.setMessage("卢本伟牛逼");
        //dc.setMessage("我来了小老弟");
        //dc.setMessage("我是布袋和尚说不得");
        xishi.setMessage("大司马是大佬比");
        xishi.setMessage("设呢么");
    }
}
