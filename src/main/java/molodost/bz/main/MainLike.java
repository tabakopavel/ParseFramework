package molodost.bz.main;

import molodost.bz.object.News;
import molodost.bz.object.User;
import molodost.bz.object.UserPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainLike {
    public static void main(String[] args) {
        UserPage userPage;
        List<News> news;
        List<User> users = new ArrayList<>();
        users.add(new User("zinovenkov@gmail.com", "miramil2009"));
        String evg = "12893835";
        String rom = "127437";
//        String evg = "292282";
//        String rom = "bm-artemanto";

        for (User user : users) {
            try {
                user.login();
                userPage = new UserPage(user, evg);
                userPage.open();
                news = userPage.getPageNews(0);
                user.like(news.get(0).getNewsId());

                userPage = new UserPage(user, rom);
                userPage.open();
                news = userPage.getPageNews(0);
                user.like(news.get(0).getNewsId());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
