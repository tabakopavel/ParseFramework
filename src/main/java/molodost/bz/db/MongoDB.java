package molodost.bz.db;

import com.mongodb.*;
import molodost.bz.object.News;
import molodost.bz.object.User;

import java.util.ArrayList;
import java.util.List;

public class MongoDB {
    private static final String MONGODB_LOCALHOST_27017 = "mongodb://localhost:27017";
    private static MongoClient mongoClient;
    private static MongoDB mongoDB;
    private DB db;
    private DBObject doc;
    private WriteResult result;
    private DBCollection dbCollection;
    private int time = 0;

    private MongoDB() {
        mongoClient = new MongoClient(new MongoClientURI(MONGODB_LOCALHOST_27017));
    }

    public static MongoDB getConnection() {
        if (mongoDB == null) {
            mongoDB = new MongoDB();
        }
        return mongoDB;

    }


    public void addNews(News news, String dataBaseName, String collectionName) {
        db = mongoClient.getDB(dataBaseName);
        dbCollection = db.getCollection(collectionName);
        DBObject doc = news.createDBObject();
        result = dbCollection.insert(doc);
    }

    public boolean checkNews(News news, String dataBaseName, String collectionName) {
        if (time != 1) {
            db = mongoClient.getDB(dataBaseName);
            dbCollection = db.getCollection(collectionName);
            time = 1;
        }
        DBObject query = BasicDBObjectBuilder.start().add("newsLink", news.getNewsLink()).get();
        return dbCollection.find(query).count() == 0;
    }


    public void addUserBot(User user, String dataBaseName, String collectionName) {
        if (checkUserBot(user, dataBaseName, collectionName)) {
            db = mongoClient.getDB(dataBaseName);
            dbCollection = db.getCollection(collectionName);
            DBObject doc = user.createDBObject();
            result = dbCollection.insert(doc);
        }
    }

    private boolean checkUserBot(User user, String dataBaseName, String collectionName) {
        if (time != 1) {
            db = mongoClient.getDB(dataBaseName);
            dbCollection = db.getCollection(collectionName);
            time = 1;
        }
        DBObject query = BasicDBObjectBuilder.start().add("login", user.getLogin()).get();
        return dbCollection.find(query).count() == 0;
    }

    public List<User> getAllUserBots(String dataBaseName, String collectionName) {
        List<User> usersBots = new ArrayList<>();
        if (time != 1) {
            db = mongoClient.getDB(dataBaseName);
            dbCollection = db.getCollection(collectionName);
            time = 1;
        }
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            usersBots.add(new User(object.get("login").toString(), object.get("password").toString()));
        }
        return usersBots;
    }

    public void close() {
        mongoClient.close();
    }


}
