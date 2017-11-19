package molodost.bz.core.ui;

import molodost.bz.core.object.News;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import util.Driver;

public class NewsElement extends HtmlElement {

    @FindBy(how = How.XPATH, xpath = ".//a[@class='user-inline__title']")
    public WebElement user;

    @FindBy(how = How.XPATH, xpath = ".//div[@class='user-inline__info']")
    public  WebElement userInfo;

    @FindBy(how = How.XPATH, xpath = ".//div[@class='marked-content']")
    public  WebElement text;

    @FindBy(how = How.XPATH, xpath = ".//a[@class='post-preview__title']")
    public  WebElement title;

    @FindBy(how = How.XPATH, xpath = ".//a[@class='show-more-text']")
    public  WebElement moreButton;


    public News getNews() {
        News news = new News();
        news.setAuthorName(user.getText());
        news.setAuthorLink(user.getAttribute("href"));
        if (Driver.isElementDisplayed(this.moreButton)) {
            moreButton.click();
            Driver.waitForJSandJQueryToLoad(2);
        }
        news.setText(text.getText());

        if (Driver.isElementDisplayed(this.title)) {
            news.setTitle(this.title.getText());
        } else {
            news.setTitle("");
        }
        return news;
    }


}
