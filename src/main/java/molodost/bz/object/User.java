package molodost.bz.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class User {

    private static final String HTTPS_PLATFORM_MOLODOST_BZ = "https://platform.molodost.bz/@";
    private static final String HTTPS_PLATFORM_MOLODOST_BZ_API_AUTH_LOGIN = "https://platform.molodost.bz/api/auth/login";
    private String login;
    private String password;

    private String id;
    private String userID;
    private String userPageLink;
    private String cookie;
    private String programID;


    public DBObject createDBObject() {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        docBuilder.append("login", login);
        docBuilder.append("password", password);
        return docBuilder.get();
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int login() throws IOException {
        URL myUrl = new URL(HTTPS_PLATFORM_MOLODOST_BZ_API_AUTH_LOGIN);
        HttpsURLConnection con = (HttpsURLConnection) myUrl.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Referer", userPageLink);
        con.setRequestProperty("Cookie", cookie);
        setStandartHeaders(con);
        con.setDoOutput(true);
        JSONObject cred = new JSONObject();
        cred.put("email", login);
        cred.put("password", password);
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(cred.toString());
        wr.flush();
        con.connect();
        int status = con.getResponseCode();
        Map<String, List<String>> map = con.getHeaderFields();
        this.cookie = createCookie(map.get("Set-Cookie").toString());
        setParam(convertStreamToString(con));
        con.disconnect();
        return status;
    }

    public int like(String postId) throws IOException {
        String httpsURL = "https://platform.molodost.bz/api/mongo/posts/" + postId + "/like";
        URL myUrl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myUrl.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Referer", userPageLink);
        con.setRequestProperty("Cookie", cookie);
        setStandartHeaders(con);
        con.connect();
        int status = con.getResponseCode();
        con.disconnect();
        return status;
    }

    private void setParam(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement userElement = jsonObject.get("user");
            JsonObject user = userElement.getAsJsonObject();
            this.id = user.get("_id").getAsString();
            this.userID = user.get("name").getAsString();
            this.userPageLink = HTTPS_PLATFORM_MOLODOST_BZ + userID;
            JsonElement program = user.get("programs");
            JsonObject programObject = program.getAsJsonArray().get(0).getAsJsonObject();
            this.programID = programObject.get("programId").getAsString();
        }
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

    private String createCookie(String line) {
        StringBuilder stringBuffer = new StringBuilder();
        String[] resultArray = line.split(" ");
        for (String s : resultArray) {
            if (s.contains("sess=")) {
                stringBuffer.append(s.replace("[", "").replace("]", "")).append(" ");
            }
            if (s.contains("sess.sig=")) {
                stringBuffer.append(s.replace("[", "").replace("]", "")).append(" ");
            }
        }
        return stringBuffer.toString();
    }

    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPageLink() {
        return userPageLink;
    }

    public String getCookie() {
        return cookie;
    }

    public String getProgramID() {
        return programID;
    }


}
