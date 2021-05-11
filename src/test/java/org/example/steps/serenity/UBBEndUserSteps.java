package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.UBBPage;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UBBEndUserSteps {
    UBBPage ubbPage;

    @Step
    public void enter_and_search(String keyword) {
        ubbPage.enter_and_search_keywords(keyword);
    }

    @Step
    public void should_see_title(String title) {
        assertThat(ubbPage.get_title(), containsString(title));
    }

    @Step
    public void should_not_see_title(String title) {
        assertThat(ubbPage.get_title(), not(containsString(title)));
    }

    @Step
    public void change_language_to_english(){
        ubbPage.click_english_button();
    }

    @Step
    public void open_home_page() {
        ubbPage.open();
    }

    @Step
    public void looks_for(String term) {
        enter_and_search(term);
    }

    public void check_undergraduate_programme(String xpath, String undergraduate_programme) {
        String text = ubbPage.find(By.xpath(xpath)).getText();
        assertThat(undergraduate_programme, containsString(text));
    }

    public void check_search_bar_is_not_in_romanian() {
        String search_bar_text = ubbPage.find(By.xpath("//*[@id=\"s\"]")).getValue();
        assertThat("Căutare", Matchers.not(search_bar_text));
    }
}
