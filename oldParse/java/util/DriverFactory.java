package util;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

//
//    private static ThreadLocal<WebDriver> remoteWebDriver = ThreadLocal.withInitial(() -> {
//
//        return new ChromeDriver(); // can be replaced with other browser drivers
//    });
//    static Logger log;
//
//    static {
//        log = Logger.getLogger(DriverFactory.class);
//    }
//
//    public static WebDriver getDriver() {
//        log.debug("Getting instance of remote driver");
//        return remoteWebDriver.get();
//    }
//
//    public static void setWebDriver(WebDriver driver) {
//        log.debug("Setting the driver");
//        remoteWebDriver.set(driver);
//    }
//
//    /**
//     * Returns a string containing current browser name, its version and OS name.
//     * This method is used in the the *WebDriverListeners to change the test name.
//     * */
//    public static String getBrowserInfo(){
//        log.debug("Getting browser info");
//        // we have to cast WebDriver object to RemoteWebDriver here, because the first one does not have a method
//        // that would tell you which browser it is driving. (sick!)
//        Capabilities cap = ((RemoteWebDriver) DriverFactory.getDriver()).getCapabilities();
//        String b = cap.getBrowserName();
//        String os = cap.getPlatform().toString();
//        String v = cap.getVersion();
//        return String.format("%s v:%s %s", b, v, os);
//    }
//



    private static JavascriptExecutor js;
//    private static Integer defImpWait=5;
//    private static Integer defExpWait;
//    private static Integer defJsWait;
//    private static String browserFromCmd;
//
//    private DriverFactory() {
//        //Do-nothing..Do not allow to initialize this class from outside
//    }
//
//    private static DriverFactory instance = new DriverFactory();
//
//    public static DriverFactory getInstance() {
//        return instance;
//    }
//
//    // thread local driver object for webdriver
//    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
//
//        return new ChromeDriver(); // can be replaced with other browser drivers
//    });
//
//    public WebDriver getDriver() // call this method to get the driver object and launch the browser
//    {
//        return driver.get();
//    }
//
//    public void removeDriver() // Quits the driver and closes the browser
//    {
//        driver.get().quit();
//        driver.remove();
//    }
//
//    private void init() {
//        Propertiess.init();
//
//        getDriver().manage().window().maximize();
//
//        if (System.getProperty("impTimeout") != null) {
//            defImpWait = Integer.parseInt(System.getProperty("impTimeout"));
//        } else {
//            defImpWait = 5;
//        }
//        if (System.getProperty("expTimeout") != null) {
//            defExpWait = Integer.parseInt(System.getProperty("impTimeout"));
//        } else {
//            defExpWait = 10;
//        }
//        if (System.getProperty("jsTimeout") != null) {
//            defJsWait = Integer.parseInt(System.getProperty("jsTimeout"));
//        } else {
//            defJsWait = 10;
//        }
//        setDefImpWait();
//    }
//
//    private  void setDefImpWait() {
//        Driver.getDriver().manage().timeouts().implicitlyWait(defImpWait, TimeUnit.SECONDS);
//    }
//
//    public static void waitForPageToLoad() {
//        String pageLoadStatus;
//        do {
//            js = (JavascriptExecutor) remoteWebDriver;
//            pageLoadStatus = (String) js.executeScript("return document.readyState");
//        } while (!pageLoadStatus.equals("complete"));
//    }

    public static WebDriver getDriver() {
        return null;
    }
}
