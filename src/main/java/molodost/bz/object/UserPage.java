package molodost.bz.object;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserPage {
    private static final String HTTPS_PLATFORM_MOLODOST_BZ_API_MONGO_USERS = "https://platform.molodost.bz/api/mongo/users/";
    private User user;
    private String userID;
    private String id;
    private String userName;
    private String userPageName;
    private String info = "";
    private String vk = "";
    private String facebook = "";
    private String instagram = "";
    private String webSite = "";
    private String point_A = "";
    private String point_B = "";


    public UserPage(User user, String userID) {
        this.user = user;
        this.userID = userID;
    }

    public int open() throws IOException {
        String httpsURL = HTTPS_PLATFORM_MOLODOST_BZ_API_MONGO_USERS + userID;
        URL myUrl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Referer", user.getUserPageLink());
        con.setRequestProperty("Cookie", user.getCookie());
        setStandartHeaders(con);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.connect();
        int status = con.getResponseCode();
        parseUserInfo(convertStreamToString(con));
        con.disconnect();
        return status;
    }

    public List<News> getPageNews(int offset) throws IOException {
        String httpsURL = "https://platform.molodost.bz/api/mongo/posts?authorIds=" + id + "&programId=" + user.getProgramID() + "&user=" + user.getId() + "&offset=" + offset;
        URL myUrl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Referer", user.getUserPageLink());
        con.setRequestProperty("Cookie", user.getCookie());
        setStandartHeaders(con);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.connect();
        List<News> news = News.getNews(convertStreamToString(con));
        con.disconnect();
        return news;
    }

    public List<User> getSubscriptionsList() {
        List<User> subscriptions = new ArrayList<>();
//        https://platform.molodost.bz/api/mongo/user/5a051d173e8fb22d224ce44e/subscriptions

        return subscriptions;
    }

    public List<User> getSubscribersList() {
        List<User> subscribers = new ArrayList<>();
//        https://platform.molodost.bz/api/mongo/user/5a051d173e8fb22d224ce44e/subscribers
        return subscribers;
    }

    private void setStandartHeaders(HttpsURLConnection con) {
        con.setRequestProperty("Accept", "application/json, text/plain, */*");
        con.setRequestProperty("Host", "platform.molodost.bz");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Content-Length", "2");
        con.setRequestProperty("Origin", "https://platform.molodost.bz");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//        con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
    }

    private String convertStreamToString(HttpsURLConnection con) throws IOException {
        String json_response = "";
        InputStreamReader in = new InputStreamReader(con.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String text = "";
        while ((text = br.readLine()) != null) {
            json_response += text;
        }
        br.close();
        in.close();
        return json_response;
    }

    private void parseUserInfo(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement resultElement = jsonObject.get("result");
            JsonElement infoElement = resultElement.getAsJsonObject().get("info").getAsJsonObject();
            JsonElement userElement = resultElement.getAsJsonObject().get("user").getAsJsonObject();
            if (!resultElement.getAsJsonObject().get("occupation").isJsonNull()) {
                JsonArray occupationElement = resultElement.getAsJsonObject().get("occupation").getAsJsonArray();
                StringBuilder sb = new StringBuilder();
                for (JsonElement element : occupationElement) {
                    sb.append(element.getAsJsonObject().get("name")).append(" ");
                }
                this.info = sb.toString();
            }
            if (!resultElement.getAsJsonObject().get("goal").isJsonNull()) {
                JsonObject goalElement = resultElement.getAsJsonObject().get("goal").getAsJsonObject();
                this.point_A = goalElement.get("a").getAsString();
                this.point_B = goalElement.get("b").getAsString();
            }
            this.facebook = infoElement.getAsJsonObject().get("facebook").getAsString();
            this.vk = infoElement.getAsJsonObject().get("vk").getAsString();
            this.instagram = infoElement.getAsJsonObject().get("instagram").getAsString();
            this.webSite = infoElement.getAsJsonObject().get("website").getAsString();
            this.userName = userElement.getAsJsonObject().get("first_name").getAsString() + "" + userElement.getAsJsonObject().get("last_name").getAsString();
            this.userPageName = userElement.getAsJsonObject().get("name").getAsString();
            this.id = userElement.getAsJsonObject().get("_id").getAsString();
        }

    }

    private List<News> parseNews(String json) {
        List<News> news = News.getNews(json);
        return news;
    }

}
