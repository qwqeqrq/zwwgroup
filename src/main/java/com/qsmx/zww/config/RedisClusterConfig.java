package com.qsmx.zww.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @描述：
 * @参数： 配置redis集群
 * @返回值：
 * @创建人： zww
 * @创建时间： 2019/11/22
 * @修改人和其它信息：
 */

/**
 * 配置一个redis集群
 */
@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)

/*public class RedisClusterConfig {
    @Bean(name = "redisCluster")
    public RedisTemplate<String, Object> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }*/

/**
 * 配置多个redis集群
 */
public class RedisClusterConfig {

    @Value("${spring.redis.cache1.cluster.nodes}")
    Set<String> nodes; //读取配置文件redis cluster nodes
    @Value("${spring.redis.cache2.cluster.nodes}")
    Set<String> nodes2;
    @Value("${spring.redis.cache3.cluster.nodes}")
    Set<String> nodes3;

    @Bean(name = "redisCluster1")
    public RedisTemplate<String, Object> redisCacheTemplate1() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(nodes); //redis cluster config by nodes
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder lettucePoolingClientConfigurationBuilder =
                LettucePoolingClientConfiguration.builder();
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();// todo  使用默认连接池 可以set 配置文件
        lettucePoolingClientConfigurationBuilder.poolConfig(poolConfig);//todo  配置
        LettucePoolingClientConfiguration clientConfig = lettucePoolingClientConfigurationBuilder.build();//创建连接池配置信息
        //todo 配置连接池设置信息
        LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(clusterConfiguration, clientConfig);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean(name = "redisCluster2")
    public RedisTemplate<String, Object> redisCacheTemplate2(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean(name = "redisCluster3")
    public RedisTemplate<String, Object> redisCacheTemplate3(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
