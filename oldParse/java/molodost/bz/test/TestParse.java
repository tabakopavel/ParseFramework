package molodost.bz.test;

import molodost.bz.core.page.HomePage;
import molodost.bz.core.page.NewsPage;
import molodost.bz.core.ui.NewsElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import util.Driver;

public class TestParse extends ParseBase {


    @Test
    public void tstRet() {
        HomePage homePage = HomePage.open();
        NewsPage newsPage = homePage.login("zinovenkov@tut.by", "miramil2009");
        for (Cookie cookie : Driver.getDriver().manage().getCookies()) {
            System.out.println(cookie.getName()+"=="+cookie.getValue());
        }
        System.out.println(Driver.getDriver().manage());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        newsPage.getNewsFeed().getNewsRows();
//        for (NewsElement newsElement : newsPage.getNewsFeed().getNewsRows()) {
//            System.out.println(newsElement.getNews().getAuthorLink());
//            System.out.println(newsElement.getNews().getAuthorName());
//            System.out.println(newsElement.getNews().getText());
//            System.out.println(newsElement.getNews().getTitle());
//            System.out.println("++++");
//        }

    }
}
