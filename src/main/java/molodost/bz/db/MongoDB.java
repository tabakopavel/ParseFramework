package molodost.bz.db;

import com.mongodb.*;
import molodost.bz.object.News;
import molodost.bz.object.User;

public class MongoDB {
    private static final String MONGODB_LOCALHOST_27017 = "mongodb://localhost:27017";
    private static MongoClient mongoClient;
    private static MongoDB mongoDB;
    private DB db;
    private DBObject doc;
    private WriteResult result;
    private DBCollection col;
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

    public boolean checkNews(News news, String dataBaseName, String collectionName) {
        if (time != 1) {
            db = mongoClient.getDB(dataBaseName);
            col = db.getCollection(collectionName);
            time = 1;
        }
        DBObject query = BasicDBObjectBuilder.start().add("newsLink", news.getNewsLink()).get();
        return col.find(query).count() == 0;
    }

    public void addNews(News news, String dataBaseName, String collectionName) {
        db = mongoClient.getDB(dataBaseName);
        col = db.getCollection(collectionName);
        DBObject doc = news.createDBObject();
        result = col.insert(doc);
    }

    public void addUserBot(User user, String dataBaseName, String collectionName) {
        db = mongoClient.getDB(dataBaseName);
        col = db.getCollection(collectionName);
        DBObject doc = user.createDBObject();
        result = col.insert(doc);
    }

    public void close() {
        mongoClient.close();
    }


}
