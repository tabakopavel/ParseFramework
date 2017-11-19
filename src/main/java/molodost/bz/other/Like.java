package molodost.bz.other;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Like {

    public static int like(String method, String postId, String referLink, String cookie) throws IOException {
        String httpsURL = "https://platform.molodost.bz/api/mongo/posts/" + postId + "/like";
        URL myUrl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myUrl.openConnection();
        con.setRequestMethod(method);
        setStandartHeaders(con);
        con.setRequestProperty("Referer", referLink);
        con.setRequestProperty("Cookie", cookie);
        con.connect();
        int status = con.getResponseCode();
        con.disconnect();
        return status;

    }

    private static void setStandartHeaders(HttpsURLConnection con) {
        con.setRequestProperty("Accept", "application/json, text/plain, */*");
        con.setRequestProperty("Host", "platform.molodost.bz");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Content-Length", "2");
        con.setRequestProperty("Accept", "application/json, text/plain, */*");
        con.setRequestProperty("Origin", "https://platform.molodost.bz");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        con.setRequestProperty("Origin", "https://platform.molodost.bz");
    }

}
