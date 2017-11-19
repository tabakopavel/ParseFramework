package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.Driver;
import util.DriverFactory;
import util.Propertiess;

import java.util.List;

public class Like extends ParseBase {

    public static void main(String[] args) {
        Like like = new Like();
        like.clickLike("https://platform.molodost.bz/@12893835");
    }

    public void clickLike(String url) {
        Propertiess.init();
        WebDriver driver = Driver.getDriver();

        driver.get("https://platform.molodost.bz/");

//        Driver.waitForPageToLoad();
        Driver.waitForElementVisible(By.xpath("//a[@class='user-menu__link']"), 5);
        driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/div/header/div/div[2]/ul/div/li[2]/a")).click();
        Driver.waitForElementVisible(By.xpath("//div[@class='login-form']"), 5);

        driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[1]/input"))
                .sendKeys("zinovenkov@tut.by");
        driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[2]/input"))
                .sendKeys("miramil2009");
        driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[3]/button")).click();

        //Driver.waitForPageToLoad();
        Driver.waitForElementVisible(By.xpath("//div[@class='user-inline user-inline_small']"), 5);

        driver.get(url);
        List<WebElement> list = driver.findElements(By.xpath("html/body/div[1]/div[1]/div/div/div/div[1]/div/div[2]/div[2]/div/div[2]/div[2]/div/div"));
        System.out.println(list.get(0).findElement(By.xpath("(//div[@class='post-interact-btn '])[1]")).getText());

        list.get(0).findElement(By.xpath("(//div[@class='post-interact-btn '])[1]")).click();
        System.out.println(list.size());
        System.out.println(list.size());
    }
}
