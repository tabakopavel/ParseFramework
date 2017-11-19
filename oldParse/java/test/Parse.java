package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import util.Driver;
import util.ExcelRead;

import java.util.ArrayList;
import java.util.List;

public class Parse extends ParseBase {


    @Test
    public void parseBoard() {


        Driver.getDriver().get("http://seocatalogs.info/board/");
        String x = "(//a[@href='/board/";
        int y = 2;
        String z = "/'])[2]";
        By page = By.xpath(x + y + z);
        String path = "D:\\boards.xls";
        ExcelRead excelRead = new ExcelRead();
        excelRead.setInputFile(path);
        excelRead.set();
        int q = 1;
        while (true) {
            List<WebElement> list = Driver.getDriver().findElements(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr/td[2]/p/a"));
            String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
            ArrayList<String> tabs;

            for (WebElement element : list) {
                element.sendKeys(selectLinkOpeninNewTab);
                tabs = new ArrayList<String>(Driver.getDriver().getWindowHandles());
                Driver.getDriver().switchTo().window(tabs.get(1));
//                Driver.waitForPageToLoad();
                String a = Driver.getDriver().findElement(By.xpath(".//*[@id='table-box']/table/tbody/tr[1]/td[2]")).getText();
                String b = Driver.getDriver().findElement(By.xpath(".//*[@id='table-box']/table/tbody/tr[2]/td[2]")).getText();
                String c = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[3]/td[2]")).getText();
                String d = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[4]/td[2]")).getText();
                String e = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[5]/td[2]")).getText();
                String f = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[6]/td[2]")).getText();
                String g = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[7]/td[2]")).getText();
                String p = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[8]/td[2]")).getText();
                String h = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[9]/td[2]")).getText();
                String j = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[10]/td[2]")).getText();
                String k = Driver.getDriver().findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div[2]/table/tbody/tr[11]/td[2]")).getText();
                Driver.getDriver().close();
                Driver.getDriver().switchTo().window(tabs.get(0));
                excelRead.write(q, a, b, c, d, e, f, g, p, h, j, k);
                q++;

            }
//            if (Driver.isElementPresent(page)) {
//                Driver.getDriver().findElement(page).click();
//                y++;
//                page = By.xpath(x + y + z);
//            } else {
//                break;
//            }
        }
//        excelRead.end();
    }
}
