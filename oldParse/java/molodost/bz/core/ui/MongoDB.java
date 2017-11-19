package molodost.bz.core.ui;

import com.mongodb.*;
import molodost.bz.core.object.News;

public class MongoDB {
    private static final String MONGODB_LOCALHOST_27017 = "mongodb://localhost:27017";
    private static MongoClient mongoClient;
    private static MongoDB mongoDB;
    private DB db;
    private DBObject doc;
    private WriteResult result;
    private DBCollection col;

    private MongoDB() {
        mongoClient = new MongoClient(new MongoClientURI(MONGODB_LOCALHOST_27017));
    }

    public static MongoDB getConnection() {
        if (mongoDB == null) {
            mongoDB = new MongoDB();
        }
        return mongoDB;

    }

    public boolean checkNews(News news) {
        DBObject query = BasicDBObjectBuilder.start().add("newsLink", news.getNewsLink()).get();
        return col.find(query).count() == 0;
    }

    public void addNews(News news, String dataBaseName, String collectionName) {
        db = mongoClient.getDB(dataBaseName);
        col = db.getCollection(collectionName);
        DBObject doc = news.createDBObject();
        result = col.insert(doc);
    }

    public void close() {
        mongoClient.close();
    }
}
