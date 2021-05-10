package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("http://www.cs.ubbcluj.ro/")
public class UBBPage extends PageObject {
    @FindBy(name="s")
    private WebElementFacade searchBar;

    @FindBy(xpath = "//li/a[contains(@href,'http://www.cs.ubbcluj.ro/en/')]")
    private WebElementFacade englishButton;

    public void enter_and_search_keywords(String keyword) {
        searchBar.typeAndEnter(keyword);
    }

    public String get_title() {
        return find(By.xpath("//h2/a")).getText();
    }

    public void click_english_button() {
        englishButton.click();
    }
}
