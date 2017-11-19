package molodost.bz.core.ui;

import molodost.bz.core.object.News;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import util.Driver;

import java.util.List;


@FindBy(how = How.XPATH, using = "//div[contains(@class,'feed__left')]")
public class NewsFeed extends HtmlElement {


    @FindAll({@FindBy(how = How.XPATH, using = "//div[@class='panel panel_margin_negative']")})
    private List<NewsElement> newsRows;

    public List<NewsElement> getNewsRows() {

        for (NewsElement newsElement : newsRows) {
            Driver.waitForElementVisible(newsElement.getWrappedElement(), 1);
            System.out.println(newsElement.user.getText());
            System.out.println(newsElement.user.getAttribute("href"));
            if (Driver.isElementDisplayed(newsElement.moreButton)) {
                newsElement.moreButton.click();
                Driver.waitForJSandJQueryToLoad(2);
            }
            System.out.println(newsElement.text.getText());

            if (Driver.isElementDisplayed(newsElement.title)) {
                System.out.println(newsElement.title.getText());
            } else {
                System.out.println("null");
            }


            System.out.println("++++");
        }

        return newsRows;
    }


}
