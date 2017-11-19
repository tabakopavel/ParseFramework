package test;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.Driver;

public class ParseBase {
    @BeforeMethod
    public void init() {
        //Driver.init();
    }


    @AfterMethod(alwaysRun = true)
    public void clean() {
        Driver.clean();
    }
}
