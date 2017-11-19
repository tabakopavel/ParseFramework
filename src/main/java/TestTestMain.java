import com.mashape.unirest.http.exceptions.UnirestException;
import molodost.bz.db.MongoDB;
import molodost.bz.object.User;
import molodost.bz.object.UserPage;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class TestTestMain {

    public static void main(String[] args) throws IOException {
        MongoDB mongoDB = MongoDB.getConnection();

        mongoDB.addUserBot(new User("zinovenkov@gmail.com", "miramil2009"), "molodost", "userBots");
        mongoDB.addUserBot(new User("ivanovevgenits@yandex.ru", "6pr5GKH7k"), "molodost", "userBots");
        mongoDB.addUserBot(new User("jarkovevgenij@yandex.ru", "miramil2009"), "molodost", "userBots");
        mongoDB.addUserBot(new User("mihpetrowitch@yandex.ru", "bHcS8J05z"), "molodost", "userBots");
        mongoDB.addUserBot(new User("firsovnickolaj@yandex.ru", "miramil2009"), "molodost", "userBots");
        mongoDB.addUserBot(new User("maksimka2017i@yandex.ru", "9gfy34BTU"), "molodost", "userBots");
        mongoDB.addUserBot(new User("maksimka2017i@mail.ru", "SoI3e3Tf6"), "molodost", "userBots");
        mongoDB.addUserBot(new User("nikolaj.gorin.86@mail.ru", "CY6zr21Ri"), "molodost", "userBots");
        mongoDB.addUserBot(new User("andrej.kachanov.88@mail.ru", "0e27RzSnC"), "molodost", "userBots");
        mongoDB.addUserBot(new User("fedorenkoerema@yandex.by", "G959BlJjt"), "molodost", "userBots");

        mongoDB.close();
//        User user = new User("zinovenkov@gmail.com", "miramil2009");
//        try {
////            https://platform.molodost.bz/posts/5a0de1a5f11af6771028be22
//            System.out.println(user.login());
////            System.out.println(user.like("5a0de1a5f11af6771028be22"));
//            UserPage userPage = new UserPage(user, "292282");
//            userPage.open();
//           userPage.getPageNews(0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


}
