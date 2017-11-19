package molodost.bz.core.ui;

import com.mongodb.*;
import molodost.bz.core.object.News;

public class TestJson1 {
    public static void main(String[] args) {

    }

    private static DBObject createDBObject(News news) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        docBuilder.append("title", news.getTitle());
        docBuilder.append("text", news.getText());
        docBuilder.append("authorName", news.getAuthorName());
        docBuilder.append("authorLink", news.getAuthorLink());
        docBuilder.append("newsLink", news.getNewsLink());
        docBuilder.append("userId", news.getUserId());
        return docBuilder.get();
    }
}
