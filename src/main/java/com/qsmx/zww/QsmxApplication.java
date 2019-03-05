package com.qsmx.zww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zww on 2019-03-05. springboot web项目启动类
 */

@SpringBootApplication
public class QsmxApplication {
    public static void main(String[] args) {
        //使用SpringApplication.run启动springboot项目
        //也可打成war包部署在Tomcat服务器，需要在这实现Sevletinint类,去掉pom相关tomcat注解、以后在体验 不推荐
        SpringApplication.run(QsmxApplication.class);
    }
}
