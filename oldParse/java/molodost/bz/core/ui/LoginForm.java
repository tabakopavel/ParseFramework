package molodost.bz.core.ui;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import util.Driver;

@FindBy(how = How.XPATH, using = "//div[@class='login-form']")
public class LoginForm extends HtmlElement {

    @FindBy(how = How.XPATH, xpath = ".//input[@class='login-form__input'and @type='text']")
    private TextInput userName;

    @FindBy(how = How.XPATH, xpath = ".//input[@class='login-form__input'and @type='password']")
    private TextInput password;

    @FindBy(how = How.XPATH, xpath = ".//button[@class='login-form__btn'and @type='submit']")
    private TextInput loginButton;

    public void login(String userName, String password) {
        Driver.waitForElementVisible(this.getWrappedElement(), 1);
        this.userName.clear();
        this.userName.sendKeys(userName);
        this.password.clear();
        this.password.sendKeys(password);
        this.loginButton.click();
        Driver.waitForPageToLoad();
    }


}
