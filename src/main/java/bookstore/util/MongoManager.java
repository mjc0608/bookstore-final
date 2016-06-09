package bookstore.util;

import com.mongodb.DBAddress;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * Created by Jachin on 6/8/16.
 */
public class MongoManager {
    private String DBAddress;
    private String DBName;
    private static MongoDatabase db;

    public MongoDatabase getMongoDatabase() {
        if(db == null) {
            MongoClientURI mongoClientURI = new MongoClientURI(DBAddress);
            MongoClient mongoClient = new MongoClient(mongoClientURI);
            db = mongoClient.getDatabase(DBName);
//            System.out.println(db);
        }
//        System.out.println(db);
        return db;
    }

    public String getDBName() {
        return DBName;
    }

    public String getDBAddress() {
        return DBAddress;
    }

    public void setDBAddress(String DBAddress) {
        this.DBAddress = DBAddress;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }
}
