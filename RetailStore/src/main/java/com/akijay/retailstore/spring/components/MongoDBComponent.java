package com.akijay.retailstore.spring.components;

import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

/**
 * Created by vijay on 10/18/16.
 */
@Component
public class MongoDBComponent {

    //private final MongoDbFactory dbFactory;

    /*@Autowired
    public MongoDBComponent(MongoDbFactory factory) {
        dbFactory = factory;
    }

    public DB getDatabase()
    {
        return dbFactory.getDb();
    }*/



}
