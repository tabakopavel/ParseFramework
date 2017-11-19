package molodost.bz.main;

import molodost.bz.db.MongoDB;
import molodost.bz.object.News;
import molodost.bz.other.HttpConnection;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.util.List;

public class MainNews {

    private static final String HTTPS_PLATFORM_MOLODOST_BZ_API_MONGO_POSTS_MODE_NEW_OFFSET = "https://platform.molodost.bz/api/mongo/posts?mode=new&offset=";
    public static boolean flag = true;

    public static void main(String[] args) {
        int newsGroup = 0;
        String json = null;
        int numberOfNews = 0;

        MongoDB mongoDB = MongoDB.getConnection();
        while (true) {
            HttpConnection httpConnection = new HttpConnection();
            try {
                json = httpConnection.sendGet(HTTPS_PLATFORM_MOLODOST_BZ_API_MONGO_POSTS_MODE_NEW_OFFSET + newsGroup);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<News> newsList = News.getNews(json);
            for (News news : newsList) {
                if (mongoDB.checkNews(news, "molodost", "news")) {
                    System.out.println(news.getNewsLink());
                    System.out.println(news.getTitle());
                    mongoDB.addNews(news, "molodost", "news");
                    numberOfNews++;
                } else {
                    flag = false;
                }
            }
            if (!flag) {
                try {
                    System.out.println(getTime());
                    System.out.println("Number of News: " + numberOfNews);
                    numberOfNews = 0;
                    Thread.sleep(1800000);
                    flag = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            newsGroup++;
        }
//        mongoDB.close();
//        System.out.println(getTime());
//        System.out.println("Number of News: " + numberOfNews);
    }

    public static String getTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(" dd MMMM yyyy HH-mm-ss.SSS");
        String dateTime = formatter.print(time);
        return dateTime;
    }
}
