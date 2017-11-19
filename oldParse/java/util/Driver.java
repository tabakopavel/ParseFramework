package util;

import org.apache.commons.io.FileUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class Driver {

    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";
    private static final String EDGE = "edge";
    private static final String IE = "ie";
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private static Integer defImpWait;
    private static Integer defJsWait;
    private static String browserFromCmd;
    private static URL url;

    private Driver() {

    }

    public static void setBrowser(String browser) {
        browserFromCmd = browser;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            init();
        }
        return driver;
    }

    private static void setImpWait(int timeout) {
        Driver.getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    private static void setDefImpWait() {
        Driver.getDriver().manage().timeouts().implicitlyWait(defImpWait, TimeUnit.SECONDS);
    }

    private static void webDriverInstCreation(String browser) {
        if (browser.equalsIgnoreCase(FIREFOX)) {
            FirefoxProfile profile = new FirefoxProfile();
            DesiredCapabilities dc = DesiredCapabilities.firefox();
            profile.setPreference("browser.startup.homepage", "about:blank");
            dc.setCapability(FirefoxDriver.PROFILE, profile);
            driver = new FirefoxDriver(dc);
        } else if (browser.equalsIgnoreCase(CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase(EDGE)) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase(IE)) {
            driver = new InternetExplorerDriver();
        } else {
            //To Do  throw Exception new
        }
    }

    private static void remWebDriverInstCreation(String browser) {
        try {
            url = new URL(System.getProperty("gridHub"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (browser.equalsIgnoreCase(FIREFOX)) {
            FirefoxProfile profile = new FirefoxProfile();
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            profile.setPreference("browser.startup.homepage", "about:blank");
            capability.setCapability(FirefoxDriver.PROFILE, profile);
            driver = new RemoteWebDriver(url, capability);
        } else if (browser.equalsIgnoreCase(CHROME)) {
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(url, capability);
        } else if (browser.equalsIgnoreCase(EDGE)) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase(IE)) {
            driver = new InternetExplorerDriver();
        } else {
            //To Do  throw Exception new
        }
    }

    public static void init() {
        Propertiess.init();
        if (browserFromCmd == null) {
            webDriverInstCreation(System.getProperty("browser"));
        } else {
            webDriverInstCreation(browserFromCmd);
        }
        Driver.getDriver().manage().window().maximize();
        if (System.getProperty("impTimeout") != null) {
            defImpWait = Integer.parseInt(System.getProperty("impTimeout"));
        } else {
            defImpWait = 5;
            setDefImpWait();
        }
        Integer defExpWait;
        if (System.getProperty("expTimeout") != null) {
            defExpWait = Integer.parseInt(System.getProperty("impTimeout"));
        } else {
            defExpWait = 10;
        }
        if (System.getProperty("jsTimeout") != null) {
            defJsWait = Integer.parseInt(System.getProperty("jsTimeout"));
        } else {
            defJsWait = 10;
        }
    }

    public static void initRem() {
        Propertiess.init();
        if (browserFromCmd == null) {
            remWebDriverInstCreation(System.getProperty("browser"));
        } else {
            remWebDriverInstCreation(browserFromCmd);
        }
        Driver.getDriver().manage().window().setSize(new Dimension(3000, 3000));
        if (System.getProperty("impTimeout") != null) {
            defImpWait = Integer.parseInt(System.getProperty("impTimeout"));
        } else {
            defImpWait = 5;
            setDefImpWait();
        }
        Integer defExpWait;
        if (System.getProperty("expTimeout") != null) {
            defExpWait = Integer.parseInt(System.getProperty("impTimeout"));
        } else {
            defExpWait = 10;
        }
        if (System.getProperty("jsTimeout") != null) {
            defJsWait = Integer.parseInt(System.getProperty("jsTimeout"));
        } else {
            defJsWait = 10;
        }
    }

    public static boolean isElementPresent(WebElement webElement) {
        setImpWait(0);
        List<WebElement> elements = getDriver().findElements(Driver.getAbsoluteXpath(webElement));
        setDefImpWait();
        return elements.size() > 0 && elements.get(0).isDisplayed();
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
             element.isDisplayed();
             return true;
        } catch(org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementVisible(By locator) {
        setImpWait(0);
        List<WebElement> elements = getDriver().findElements(locator);
        setDefImpWait();
        return elements.size() > 0 && elements.get(0).isDisplayed();
    }

    public static boolean isElementClickable(WebElement webElement, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static By getAbsoluteXpath(WebElement webElement) {
        String xpath = (String) ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "function absoluteXPath(element) {" +
                        "var comp, comps = [];" +
                        "var parent = null;" +
                        "var xpath = '';" +
                        "var getPos = function(element) {" +
                        "var position = 1, curNode;" +
                        "if (element.nodeType == Node.ATTRIBUTE_NODE) {" +
                        "return null;" +
                        "}" +
                        "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {" +
                        "if (curNode.nodeName == element.nodeName) {" +
                        "++position;" +
                        "}" +
                        "}" +
                        "return position;" +
                        "};" +

                        "if (element instanceof Document) {" +
                        "return '/';" +
                        "}" +

                        "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode){" +
                        "comp = comps[comps.length] = {};" +
                        "switch (element.nodeType) {" +
                        "case Node.TEXT_NODE:" +
                        "comp.name = 'text()';" +
                        "break;" +
                        "case Node.ATTRIBUTE_NODE:" +
                        "comp.name = '@' + element.nodeName;" +
                        "break;" +
                        "case Node.PROCESSING_INSTRUCTION_NODE:" +
                        "comp.name = 'processing-instruction()';" +
                        "break;" +
                        "case Node.COMMENT_NODE:" +
                        "comp.name = 'comment()';" +
                        "break;" +
                        "case Node.ELEMENT_NODE:" +
                        "comp.name = element.nodeName;" +
                        "break;" +
                        "}" +
                        "comp.position = getPos(element);" +
                        "}" +

                        "for (var i = comps.length - 1; i >= 0; i--) {" +
                        "comp = comps[i];" +
                        "xpath += '/' + comp.name.toLowerCase();" +
                        "if (comp.position !== null) {" +
                        "xpath += '[' + comp.position + ']';" +
                        "}" +
                        "}" +

                        "return xpath;" +

                        "} return absoluteXPath(arguments[0]);", webElement);
        return By.xpath(xpath);
    }

    public static void pointAt(WebElement webElement) {
        Coordinates cor = ((Locatable) webElement).getCoordinates();
        cor.onPage();
        cor.inViewPort();
    }

    public static void waitForElementVisible(By locator, int timeout) {
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void waitForElementVisible(WebElement webElement, int timeout) {
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementVisible(List<WebElement> listOfWebElement, int timeout) {
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.visibilityOfAllElements(listOfWebElement));
    }

    public static void waitForElementClickable(By locator, int timeout) {
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementClickable(WebElement webElement, int timeout) {
        new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void highLightElement(By locator) {
        js.executeScript("argument[0].style.border='5px solid red'", getDriver().findElement(locator));
    }

    public static void unHighLightElement(By locator) {
        js.executeScript("argument[0].style.border='0px'", getDriver().findElement(locator));
    }

    public static boolean waitForJSandJQueryToLoad(int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public static void waitForAjax() {
        try {
            WebDriverWait driverWait = new WebDriverWait(getDriver(), defJsWait);
            ExpectedCondition<Boolean> expectation;
            expectation = new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driverjs) {

                    JavascriptExecutor js = (JavascriptExecutor) driverjs;
                    return js.executeScript("return((window.jQuery != null) && (jQuery.active == 0))").equals("true");
                }
            };
            driverWait.until(expectation);
        } catch (TimeoutException exTimeout) {
            // exTimeout.printStackTrace();
        } catch (WebDriverException exWebDriverException) {
            // exWebDriverException.printStackTrace();
        }

    }

    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active == 0);");
            }
        };
    }

    public static void clean() {
        if (driver != null) {
            Driver.getDriver().quit();
        }
    }

    public static void takeScreenShot() {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = System.getProperty("screenshotTemp") + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            //ToDo logger
        } catch (IOException e) {
            e.printStackTrace();
            //ToDo logger
        }

    }

    public static void takeScreenShotDateTime() {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormat.forPattern(" dd MMMM yyyy HH-mm-ss.SSS");
            String dateTime = formatter.print(time);
            String screenshotName = System.getProperty("screenshotTemp") + dateTime;
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            // ToDo logger
        } catch (IOException e) {
            e.printStackTrace();
            // ToDo logger
        }

    }

    public static void waitForPageToLoad() {
        String pageLoadStatus;
        do {
            js = (JavascriptExecutor) Driver.getDriver();
            pageLoadStatus = (String) js.executeScript("return document.readyState");
        } while (!pageLoadStatus.equals("complete"));
    }
}
