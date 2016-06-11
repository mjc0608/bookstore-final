package bookstore.util;

import com.mongodb.DBAddress;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by Jachin on 6/8/16.
 */
public class MongoManager {
    private static MongoDatabase db = initMongoDatabase();

    public static MongoDatabase initMongoDatabase() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"});
        MongoConfig mongoConfig = context.getBean(MongoConfig.class);

        MongoClient mongoClient = new MongoClient(mongoConfig.getHostname(), mongoConfig.getPort());
        MongoDatabase db = mongoClient.getDatabase(mongoConfig.getDbName());
        return db;
    }

    public static MongoDatabase getMongoDatabase() {
        return db;
    }

}
