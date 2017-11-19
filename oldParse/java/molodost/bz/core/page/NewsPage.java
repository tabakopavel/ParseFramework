package molodost.bz.core.page;

import molodost.bz.core.ui.NewsFeed;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import util.Driver;

public class NewsPage {


    private NewsFeed newsFeed;

    private static final String HTTPS_PLATFORM_MOLODOST_BZ_FEED_NEW = "https://platform.molodost.bz/feed/new";

    public NewsPage() {
        HtmlElementLoader.populatePageObject(this, Driver.getDriver());
    }

    public NewsPage open() {
        Driver.getDriver().get(HTTPS_PLATFORM_MOLODOST_BZ_FEED_NEW);
        return new NewsPage();
    }

    public NewsFeed getNewsFeed() {
        return newsFeed;
    }


}
