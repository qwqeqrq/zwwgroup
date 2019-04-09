package com.qsmx.zww.demo.java8;

import java.util.Optional;

/**
 * Created by zww on 2019-02-27.java 8 lambda表达式
 * <p>
 * <p>
 * 练习函数式编程
 */
interface Lambda<T> {
    T getString();
}

public class LamdaTest {

//TODO 函数式编程的点 在于匿名的 方法体实现，随时实现 随时调用 ，注意函数式接口只能有一个方法
    public static void main(String[] args) {
        int count = 0;
        Lambda ss = () -> "卧槽这个怎么就好了呢";
        Lambda<Float> lol = () -> 1.0365f;
        Lambda<String> text = () -> null;//我在这里实现了唯一的接口函数，用lambda的对象接受，可以进行函数式调用
        //System.out.println(lol.getString().toString()+ss.getString());
        String s = null;
        System.out.println(Optional.ofNullable(text.getString()).orElse("如果我是一个null，我这里需要一个lambda表达式做参数"));
    }
}
