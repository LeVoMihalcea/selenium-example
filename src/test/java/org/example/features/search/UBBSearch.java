package org.example.features.search;


import au.com.bytecode.opencsv.CSVReader;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.UBBEndUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class UBBSearch {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public UBBEndUserSteps ubbUser;

    @Test
    public void searching_by_keyword_should_display_the_corresponding_article_title() {
        ubbUser.open_home_page();
        List<List<String>> csv = getCSV("correct_data.csv");
        for(List<String> entry: csv){
            ubbUser.looks_for(entry.get(0));
            ubbUser.should_see_title(entry.get(1));
        }
    }

    @Test
    public void searching_by_keyword_should_not_display_the_corresponding_article_title() {
        ubbUser.open_home_page();
        List<List<String>> csv = getCSV("incorrect_data.csv");
        for(List<String> entry: csv){
            ubbUser.looks_for(entry.get(0));
            ubbUser.should_not_see_title(entry.get(1));
        }
    }

    @Test
    public void change_language_to_english_and_check_undergraduate_programmes() {
        ubbUser.open_home_page();
        ubbUser.change_language_to_english();
        List<List<String>> csv = getCSV("undergraduate_programmes.csv");
        for(List<String> entry: csv)
            ubbUser.check_undergraduate_programme(entry.get(0), entry.get(1));
    }

    @Test
    public void change_language_to_english_should_not_display_in_romanian() {
        ubbUser.open_home_page();
        ubbUser.change_language_to_english();
        ubbUser.check_search_bar_is_not_in_romanian();
    }

    private List<List<String>> getCSV(String filename){
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filename, StandardCharsets.UTF_8));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
