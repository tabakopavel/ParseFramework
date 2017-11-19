package molodost.bz.core.page;

import molodost.bz.core.ui.LoginForm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import util.Driver;

public class HomePage {

    private LoginForm loginForm;

    @FindBy(how = How.XPATH, xpath = "(//a[@class='user-menu__link'])[2]")
    private WebElement enterButton;

    private static final String HTTPS_PLATFORM_MOLODOST_BZ_FEED_NEW = "https://platform.molodost.bz/feed/new";

    private HomePage() {
        HtmlElementLoader.populatePageObject(this, Driver.getDriver());
    }

    public static HomePage open() {
        Driver.getDriver().get(HTTPS_PLATFORM_MOLODOST_BZ_FEED_NEW);
        return new HomePage();
    }

    public NewsPage login(String userName, String password) {
        Driver.waitForElementClickable(enterButton, 1);
        enterButton.click();
        loginForm.login(userName, password);
        Driver.waitForPageToLoad();
//        Driver.waitForJSandJQueryToLoad(3)
        return new NewsPage();
    }
}
