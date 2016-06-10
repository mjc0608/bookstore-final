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
    private static MongoClient mongoClient;

    public MongoDatabase getMongoDatabase() {
        if(mongoClient==null && db==null) {
            MongoClientURI mongoClientURI = new MongoClientURI(DBAddress);
            mongoClient = new MongoClient(mongoClientURI);
            db = mongoClient.getDatabase(DBName);
        }
        else if (db==null) {
            db=mongoClient.getDatabase(DBName);
        }
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
