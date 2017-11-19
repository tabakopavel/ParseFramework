package molodost.bz.core.object;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class News {

    private String newsId;
    private String authorName;
    private String authorLink;
    private String text;
    private String title;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public DBObject createDBObject() {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        docBuilder.append("title", getTitle());
        docBuilder.append("text", getText());
        docBuilder.append("authorName", getAuthorName());
        docBuilder.append("authorLink", getAuthorLink());
        docBuilder.append("newsLink", getNewsLink());
        docBuilder.append("userId", getUserId());
        return docBuilder.get();
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsLink() {
        return newsId;
    }

    public void setNewsLink(String newsId) {
        this.newsId = newsId;
    }
}
