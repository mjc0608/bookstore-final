package bookstore.DAO.implementation;

import bookstore.DAO.ImageDAO;
import bookstore.util.FileUtil;
import bookstore.util.MongoManager;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.client.*;
import com.mongodb.gridfs.GridFS;
import com.sun.javadoc.Doc;
import org.bson.Document;
import org.bson.types.Binary;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.UUID;

/**
 * Created by Jachin on 6/9/16.
 */
public class ImageDAOImpl implements ImageDAO {
    public byte[] getImage(String imageID) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"});
        MongoManager mongoManager = context.getBean(MongoManager.class);
        MongoDatabase db = mongoManager.getMongoDatabase();
        MongoCollection coll = db.getCollection("image");

        Document key = new Document("imageID", imageID);
        FindIterable<Document> iterable = coll.find(key);
        MongoCursor<Document> cursor = iterable.iterator();

        if (cursor.hasNext()) {
            Document doc = cursor.next();
            Binary binary = doc.get("file", Binary.class);
            return binary.getData();
        }
        else {
            return null;
        }
    }

    public String storeImage(File image) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"});
        MongoManager mongoManager = context.getBean(MongoManager.class);
        MongoDatabase db = mongoManager.getMongoDatabase();
        if (db==null) return null;
        MongoCollection coll = db.getCollection("image");
        if (coll==null) return null;

        UUID uuid=UUID.randomUUID();
        String imageID=uuid.toString();
        imageID=imageID.replace("-","");

        byte[] fbytes=FileUtil.fileToBytes(image);

        Document document = new Document();
        document.put("imageID", imageID);
        document.put("file", fbytes);

        coll.insertOne(document);

        return imageID;

    }

    public String storeImage(byte[] fbytes) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"});
        MongoManager mongoManager = context.getBean(MongoManager.class);
        MongoDatabase db = mongoManager.getMongoDatabase();
        MongoCollection coll = db.getCollection("image");

        UUID uuid=UUID.randomUUID();
        String imageID=uuid.toString();
        imageID=imageID.replace("-","");

        Document document = new Document();
        document.put("imageID", imageID);
        document.put("file", fbytes);

        coll.insertOne(document);

        return imageID;
    }
}
