package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import util.Driver;
import util.DriverFactory;
import util.ExcelRead;
import util.Propertiess;

import java.util.ArrayList;
import java.util.List;

public class ParseMolod extends ParseBase {

    @Test
    public void tstParse() {
        Propertiess.init();
        WebDriver driver = Driver.getDriver();
        String path = "D:\\molodost.xls";
        ExcelRead excelRead = new ExcelRead();
        excelRead.setInputFile(path);
        excelRead.set();


        driver.get("https://platform.molodost.bz/rating/polks");

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/li[2]/a")).click();
        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[1]/input"))
                .sendKeys("zinovenkov@tut.by");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[2]/input"))
                .sendKeys("miramil2009");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[3]/button")).click();

        Driver.waitForPageToLoad();

        WebElement table = driver.findElement(By.xpath("(//div[@class='panel'])[1]"));
        List<WebElement> groups = table.findElements(By.xpath(".//div[@class='user-row']//a"));
        System.out.println(groups.size());

        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

        ArrayList<String> tabs;
        ArrayList<String> tabs1;

        WebElement group = groups.get(0);

        group.sendKeys(selectLinkOpeninNewTab);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath("(.//div[@class='text-center'])[1]")).click();

        List<WebElement> people = driver.findElement(By.xpath(".//div[@class='modal__content']")).findElements(By.xpath(".//a[@class='group-up']"));
        int number = 1;

        for (WebElement person : people) {
            person.sendKeys(selectLinkOpeninNewTab);
            tabs1 = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(2));

            Driver.waitForPageToLoad();

            String name = "";
            String ageAndInfo = "";
            String vk = "";
            String facebook = "";
            String insta = "";
            String webSite = "";
            String pointA = "";
            String pointB = "";
            String currentUrl = "";
            try {
                name = driver.findElement(By.xpath(".//h2[@class='user-badge__name']")).getText();
            } catch (Exception e) {

            }

            try {
                for (WebElement webElement : driver.findElements(By.xpath(".//div[@class='user-badge__info']"))) {
                    ageAndInfo = ageAndInfo.concat(" ").concat(webElement.getText());
                }
            } catch (Exception e) {

            }
            try {
                vk = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-vk']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                facebook = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-facebook']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                insta = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-instagram']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                webSite = driver.findElement(By.xpath(".//a[@class='user-badge__info-website']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                pointA = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_a']")).getText();
            } catch (Exception e) {

            }
            try {
                pointB = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_b']")).getText();
            } catch (Exception e) {

            }
            try {
                currentUrl = driver.getCurrentUrl();
            } catch (Exception e) {

            }

            excelRead.writeModel(number, name, ageAndInfo, vk, facebook, insta, webSite, pointA, pointB, currentUrl);
            number++;
            driver.close();
            driver.switchTo().window(tabs.get(1));
        }
        excelRead.end();

    }

    @Test
    public void tstParse1() {
        Propertiess.init();
        WebDriver driver = Driver.getDriver();
        String path = "D:\\molodost1.xls";
        ExcelRead excelRead = new ExcelRead();
        excelRead.setInputFile(path);
        excelRead.set();


        driver.get("https://platform.molodost.bz/rating/polks");

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/li[2]/a")).click();
        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[1]/input"))
                .sendKeys("zinovenkov@tut.by");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[2]/input"))
                .sendKeys("miramil2009");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[3]/button")).click();

        Driver.waitForPageToLoad();

        WebElement table = driver.findElement(By.xpath("(//div[@class='panel'])[1]"));
        List<WebElement> groups = table.findElements(By.xpath(".//div[@class='user-row']//a"));
        System.out.println(groups.size());

        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

        ArrayList<String> tabs;
        ArrayList<String> tabs1;

        WebElement group = groups.get(1);

        group.sendKeys(selectLinkOpeninNewTab);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath("(.//div[@class='text-center'])[1]")).click();

        List<WebElement> people = driver.findElement(By.xpath(".//div[@class='modal__content']")).findElements(By.xpath(".//a[@class='group-up']"));
        int number = 1;

        for (WebElement person : people) {
            System.out.println(number);
            person.sendKeys(selectLinkOpeninNewTab);
            tabs1 = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(2));

            Driver.waitForPageToLoad();

            String name = "";
            String ageAndInfo = "";
            String vk = "";
            String facebook = "";
            String insta = "";
            String webSite = "";
            String pointA = "";
            String pointB = "";
            String currentUrl = "";
            try {
                name = driver.findElement(By.xpath(".//h2[@class='user-badge__name']")).getText();
            } catch (Exception e) {

            }

            try {
                for (WebElement webElement : driver.findElements(By.xpath(".//div[@class='user-badge__info']"))) {
                    ageAndInfo = ageAndInfo.concat(" ").concat(webElement.getText());
                }
            } catch (Exception e) {

            }
            try {
                vk = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-vk']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                facebook = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-facebook']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                insta = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-instagram']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                webSite = driver.findElement(By.xpath(".//a[@class='user-badge__info-website']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                pointA = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_a']")).getText();
            } catch (Exception e) {

            }
            try {
                pointB = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_b']")).getText();
            } catch (Exception e) {

            }
            try {
                currentUrl = driver.getCurrentUrl();
            } catch (Exception e) {

            }

            excelRead.writeModel(number, name, ageAndInfo, vk, facebook, insta, webSite, pointA, pointB, currentUrl);
            number++;
            driver.close();
            driver.switchTo().window(tabs.get(1));
        }
        excelRead.end();

    }

    @Test
    public void tstParse2() {
        Propertiess.init();
        WebDriver driver = Driver.getDriver();
        String path = "D:\\molodost2.xls";
        ExcelRead excelRead = new ExcelRead();
        excelRead.setInputFile(path);
        excelRead.set();


        driver.get("https://platform.molodost.bz/rating/polks");

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/li[2]/a")).click();
        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[1]/input"))
                .sendKeys("zinovenkov@tut.by");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[2]/input"))
                .sendKeys("miramil2009");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[3]/button")).click();

        Driver.waitForPageToLoad();

        WebElement table = driver.findElement(By.xpath("(//div[@class='panel'])[1]"));
        List<WebElement> groups = table.findElements(By.xpath(".//div[@class='user-row']//a"));
        System.out.println(groups.size());

        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

        ArrayList<String> tabs;
        ArrayList<String> tabs1;

        WebElement group = groups.get(2);

        group.sendKeys(selectLinkOpeninNewTab);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath("(.//div[@class='text-center'])[1]")).click();

        List<WebElement> people = driver.findElement(By.xpath(".//div[@class='modal__content']")).findElements(By.xpath(".//a[@class='group-up']"));
        int number = 1;

        for (WebElement person : people) {
            person.sendKeys(selectLinkOpeninNewTab);
            tabs1 = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(2));
            System.out.println(number);
            Driver.waitForPageToLoad();

            String name = "";
            String ageAndInfo = "";
            String vk = "";
            String facebook = "";
            String insta = "";
            String webSite = "";
            String pointA = "";
            String pointB = "";
            String currentUrl = "";
            try {

                name = driver.findElement(By.xpath(".//h2[@class='user-badge__name']")).getText();
            } catch (Exception e) {

            }

            try {
                for (WebElement webElement : driver.findElements(By.xpath(".//div[@class='user-badge__info']"))) {
                    ageAndInfo = ageAndInfo.concat(" ").concat(webElement.getText());
                }
            } catch (Exception e) {

            }
            try {

                vk = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-vk']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                facebook = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-facebook']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {

                insta = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-instagram']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {

                webSite = driver.findElement(By.xpath(".//a[@class='user-badge__info-website']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {

                pointA = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_a']")).getText();
            } catch (Exception e) {

            }
            try {
                pointB = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_b']")).getText();
            } catch (Exception e) {

            }
            try {
                currentUrl = driver.getCurrentUrl();
            } catch (Exception e) {

            }

            excelRead.writeModel(number, name, ageAndInfo, vk, facebook, insta, webSite, pointA, pointB, currentUrl);
            number++;
            driver.close();
            driver.switchTo().window(tabs.get(1));
        }
        excelRead.end();

    }

    @Test
    public void tstParse3() {
        Propertiess.init();
        WebDriver driver = Driver.getDriver();
        String path = "D:\\molodost3.xls";
        ExcelRead excelRead = new ExcelRead();
        excelRead.setInputFile(path);
        excelRead.set();


        driver.get("https://platform.molodost.bz/rating/polks");

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/li[2]/a")).click();
        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[1]/input"))
                .sendKeys("zinovenkov@tut.by");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[2]/input"))
                .sendKeys("miramil2009");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[3]/button")).click();

        Driver.waitForPageToLoad();

        WebElement table = driver.findElement(By.xpath("(//div[@class='panel'])[1]"));
        List<WebElement> groups = table.findElements(By.xpath(".//div[@class='user-row']//a"));
        System.out.println(groups.size());

        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

        ArrayList<String> tabs;
        ArrayList<String> tabs1;

        WebElement group = groups.get(3);

        group.sendKeys(selectLinkOpeninNewTab);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath("(.//div[@class='text-center'])[1]")).click();

        List<WebElement> people = driver.findElement(By.xpath(".//div[@class='modal__content']")).findElements(By.xpath(".//a[@class='group-up']"));
        int number = 1;

        for (WebElement person : people) {
            person.sendKeys(selectLinkOpeninNewTab);
            tabs1 = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(2));

            Driver.waitForPageToLoad();

            String name = "";
            String ageAndInfo = "";
            String vk = "";
            String facebook = "";
            String insta = "";
            String webSite = "";
            String pointA = "";
            String pointB = "";
            String currentUrl = "";
            try {
                name = driver.findElement(By.xpath(".//h2[@class='user-badge__name']")).getText();
            } catch (Exception e) {

            }

            try {
                for (WebElement webElement : driver.findElements(By.xpath(".//div[@class='user-badge__info']"))) {
                    ageAndInfo = ageAndInfo.concat(" ").concat(webElement.getText());
                }
            } catch (Exception e) {

            }
            try {
                vk = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-vk']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                facebook = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-facebook']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                insta = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-instagram']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                webSite = driver.findElement(By.xpath(".//a[@class='user-badge__info-website']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                pointA = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_a']")).getText();
            } catch (Exception e) {

            }
            try {
                pointB = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_b']")).getText();
            } catch (Exception e) {

            }
            try {
                currentUrl = driver.getCurrentUrl();
            } catch (Exception e) {

            }

            excelRead.writeModel(number, name, ageAndInfo, vk, facebook, insta, webSite, pointA, pointB, currentUrl);
            number++;
            driver.close();
            driver.switchTo().window(tabs.get(1));
        }
        excelRead.end();

    }

    @Test
    public void tstParse4() {
        Propertiess.init();
        WebDriver driver = Driver.getDriver();
        String path = "D:\\molodost4.xls";
        ExcelRead excelRead = new ExcelRead();
        excelRead.setInputFile(path);
        excelRead.set();


        driver.get("https://platform.molodost.bz/rating/polks");

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/li[2]/a")).click();
        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[1]/input"))
                .sendKeys("zinovenkov@tut.by");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[2]/input"))
                .sendKeys("miramil2009");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[3]/button")).click();

        Driver.waitForPageToLoad();

        WebElement table = driver.findElement(By.xpath("(//div[@class='panel'])[1]"));
        List<WebElement> groups = table.findElements(By.xpath(".//div[@class='user-row']//a"));
        System.out.println(groups.size());

        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

        ArrayList<String> tabs;
        ArrayList<String> tabs1;

        WebElement group = groups.get(4);

        group.sendKeys(selectLinkOpeninNewTab);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath("(.//div[@class='text-center'])[1]")).click();

        List<WebElement> people = driver.findElement(By.xpath(".//div[@class='modal__content']")).findElements(By.xpath(".//a[@class='group-up']"));
        int number = 1;

        for (WebElement person : people) {
            person.sendKeys(selectLinkOpeninNewTab);
            tabs1 = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(2));

            Driver.waitForPageToLoad();

            String name = "";
            String ageAndInfo = "";
            String vk = "";
            String facebook = "";
            String insta = "";
            String webSite = "";
            String pointA = "";
            String pointB = "";
            String currentUrl = "";
            try {
                name = driver.findElement(By.xpath(".//h2[@class='user-badge__name']")).getText();
            } catch (Exception e) {

            }

            try {
                for (WebElement webElement : driver.findElements(By.xpath(".//div[@class='user-badge__info']"))) {
                    ageAndInfo = ageAndInfo.concat(" ").concat(webElement.getText());
                }
            } catch (Exception e) {

            }
            try {
                vk = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-vk']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                facebook = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-facebook']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                insta = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-instagram']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                webSite = driver.findElement(By.xpath(".//a[@class='user-badge__info-website']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                pointA = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_a']")).getText();
            } catch (Exception e) {

            }
            try {
                pointB = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_b']")).getText();
            } catch (Exception e) {

            }
            try {
                currentUrl = driver.getCurrentUrl();
            } catch (Exception e) {

            }

            excelRead.writeModel(number, name, ageAndInfo, vk, facebook, insta, webSite, pointA, pointB, currentUrl);
            number++;
            driver.close();
            driver.switchTo().window(tabs.get(1));
        }
        excelRead.end();

    }

    @Test
    public void tstParse5() {
        Propertiess.init();
        WebDriver driver = Driver.getDriver();
        String path = "D:\\molodost5.xls";
        ExcelRead excelRead = new ExcelRead();
        excelRead.setInputFile(path);
        excelRead.set();


        driver.get("https://platform.molodost.bz/rating/polks");

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/li[2]/a")).click();
        Driver.waitForPageToLoad();

        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[1]/input"))
                .sendKeys("zinovenkov@tut.by");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[2]/input"))
                .sendKeys("miramil2009");
        driver.findElement(By.xpath(".//*[@id='__next']/div/div/div/header/div/div[2]/ul/div/div/div/div/div/div/form/div[3]/button")).click();

        Driver.waitForPageToLoad();

        WebElement table = driver.findElement(By.xpath("(//div[@class='panel'])[1]"));
        List<WebElement> groups = table.findElements(By.xpath(".//div[@class='user-row']//a"));
        System.out.println(groups.size());

        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

        ArrayList<String> tabs;
        ArrayList<String> tabs1;

        WebElement group = groups.get(5);

        group.sendKeys(selectLinkOpeninNewTab);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        Driver.waitForPageToLoad();

        driver.findElement(By.xpath("(.//div[@class='text-center'])[1]")).click();

        List<WebElement> people = driver.findElement(By.xpath(".//div[@class='modal__content']")).findElements(By.xpath(".//a[@class='group-up']"));
        int number = 1;

        for (WebElement person : people) {
            person.sendKeys(selectLinkOpeninNewTab);
            tabs1 = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(2));

            Driver.waitForPageToLoad();

            String name = "";
            String ageAndInfo = "";
            String vk = "";
            String facebook = "";
            String insta = "";
            String webSite = "";
            String pointA = "";
            String pointB = "";
            String currentUrl = "";
            try {
                name = driver.findElement(By.xpath(".//h2[@class='user-badge__name']")).getText();
            } catch (Exception e) {

            }

            try {
                for (WebElement webElement : driver.findElements(By.xpath(".//div[@class='user-badge__info']"))) {
                    ageAndInfo = ageAndInfo.concat(" ").concat(webElement.getText());
                }
            } catch (Exception e) {

            }
            try {
                vk = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-vk']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                facebook = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-facebook']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                insta = driver.findElement(By.xpath(".//a[@class='user-badge__info-link user-badge__info-instagram']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                webSite = driver.findElement(By.xpath(".//a[@class='user-badge__info-website']")).getAttribute("href");
            } catch (Exception e) {

            }
            try {
                pointA = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_a']")).getText();
            } catch (Exception e) {

            }
            try {
                pointB = driver.findElement(By.xpath(".//div[@class='profile-goal']//div[@class='profile-goal__block profile-goal__block_b']")).getText();
            } catch (Exception e) {

            }
            try {
                currentUrl = driver.getCurrentUrl();
            } catch (Exception e) {

            }

            excelRead.writeModel(number, name, ageAndInfo, vk, facebook, insta, webSite, pointA, pointB, currentUrl);
            number++;
            driver.close();
            driver.switchTo().window(tabs.get(1));
        }
        excelRead.end();

    }


}
