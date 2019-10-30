package com.qsmx.zww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zww on 2019-03-05. springboot web项目启动类
 * 2019年3月7日10:56:53 上注册中心注册服务
 */

@SpringBootApplication
//@EnableEurekaClient
public class QsmxApplication {
    public static void main(String[] args) {
        //使用SpringApplication.run启动springboot项目
        //也可打成war包部署在Tomcat服务器，需要在这实现Sevletinint类,去掉pom相关tomcat注解、以后在体验 不推荐
        SpringApplication.run(QsmxApplication.class);
    }

    /**
     * springboot 内嵌Tomcat 新增HTTP请求进行非法字符过滤 现在放行部分字符
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }
}
