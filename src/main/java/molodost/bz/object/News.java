package molodost.bz.object;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

public class News {

    private String newsId;
    private String authorName;
    private String authorLink;
    private String text;
    private String title;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsId() {
        return newsId;
    }

    public static List<News> getNews(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        List<News> newsList = new ArrayList<News>();
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject(); // get all json

            JsonElement resultElement = jsonObject.get("result");//get element result

            JsonObject resultObject = resultElement.getAsJsonObject();//get post object

            JsonElement posts = resultObject.get("posts"); // get element post
            JsonElement users = resultObject.get("users"); // get element post

            JsonArray postsArray = posts.getAsJsonArray();

            for (JsonElement element : postsArray) {
                News news = new News();
                news.setTitle(element.getAsJsonObject().get("title").getAsString());
                news.setText(element.getAsJsonObject().get("content").toString().replace("\\n", " "));
                news.setNewsLink("https://platform.molodost.bz/posts/" + element.getAsJsonObject().get("id").getAsString());
                news.setUserId(element.getAsJsonObject().get("userId").getAsString());
                news.setNewsId(element.getAsJsonObject().get("_id").getAsString());


                for (String key : users.getAsJsonObject().keySet()) {
                    if (key.equalsIgnoreCase(news.getUserId())) {
                        JsonElement jsonElement1 = users.getAsJsonObject().get(news.getUserId());
                        JsonObject userInfo = jsonElement1.getAsJsonObject();
                        news.setAuthorLink("https://platform.molodost.bz/@" + userInfo.get("name").toString().replace("\"", ""));
                        news.setAuthorName(userInfo.get("first_name").toString().replace("\"", "") + " " + userInfo.get("last_name").toString().replace("\"", ""));


                    }
                }
                newsList.add(news);
            }
        }
        return newsList;
    }
}
