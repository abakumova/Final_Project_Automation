package com.automation.pages;

import com.automation.driver.WebDriverManager;
import com.automation.fragments.SearchResultItem;
import com.automation.waiter.Wait;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
public class SearchResultsPage extends BasePage {

    @Timeout(15)
    @FindBy(className = "product__list--wrapper")
    private HtmlElement catalogSection;

    @FindBy(xpath = "//*[@id='perPageForm']//button")
    private Select quantitySelect;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 24)]")
    private Button quantityButton;

    private List<SearchResultItem> searchResultItemList;

    public SearchResultsPage() {
        super(() -> {
            Wait.scrollPageDownSearch();
            Wait.untilAppear(By.className("product__list--wrapper"), 150);
            WebDriverManager.getDriver().manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);
        });
    }

    public List<String> getSearchResultItemsNames() {
        List<String> names = new ArrayList<>();
        searchResultItemList.forEach(item -> names.add(item.getItemName()));
        return names;
    }

    public void changeQuantityOfItemsOnPage() {
        quantitySelect.click();
        quantityButton.click();
    }
}