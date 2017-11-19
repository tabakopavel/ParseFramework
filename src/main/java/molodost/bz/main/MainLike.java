package molodost.bz.main;

import molodost.bz.db.MongoDB;
import molodost.bz.object.News;
import molodost.bz.object.User;
import molodost.bz.object.UserPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainLike {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ooops, there is no arguments");
            System.exit(1);
        } else {
            String newsID = args[0];
            System.out.println("Like post " + newsID);
            MongoDB mongoDB = MongoDB.getConnection();
            UserPage userPage;
            List<News> news;
            List<User> users = mongoDB.getAllUserBots("molodost", "userBots");
//            String evg = "12893835";
//            String rom = "127437";

            for (User user : users) {
                try {
                    System.out.println("Work as: " + user.getLogin());
                    user.login();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
//            for (User user : users) {
//                try {
//                    System.out.println("Work as: " + user.getLogin());
//                    user.login();
//                    userPage = new UserPage(user, evg);
//                    userPage.open();
//                    news = userPage.getPageNews(0);
//                    user.like(news.get(0).getNewsId());
//
//                    userPage = new UserPage(user, rom);
//                    userPage.open();
//                    news = userPage.getPageNews(0);
//                    user.like(news.get(0).getNewsId());
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
            mongoDB.close();
        }
    }
}
