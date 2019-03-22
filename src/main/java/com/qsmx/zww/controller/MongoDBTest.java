package com.qsmx.zww.controller;

import com.qsmx.zww.po.MongoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-03-21.
 */
@RestController
@RequestMapping(value = "mongo")
public class MongoDBTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "find")
    public String findMongo(@RequestBody Map<String,String> map) {
        Query query = new Query(Criteria.where("_id").is(3));
        List<MongoInfo> mongoInfo = (List<MongoInfo>) mongoTemplate.find(query, MongoInfo.class);
        //Object object = mongoTemplate.findAll(MongoInfo.class,"duixiang");
        return mongoInfo.toString();
    }
}
