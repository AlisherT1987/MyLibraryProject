package com.library.step_definitions;

import com.library.pages.BasePage;
import com.library.pages.BooksPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class US03_StepDefs {
BooksPage booksPage=new BooksPage();

    @When("the user navigates to {string} page")
    public void theUserNavigatesToPage(String name) {
        BrowserUtil.waitFor(2);
        booksPage.navigateModule(name);
        BrowserUtil.waitFor(2);
        Assert.assertTrue(booksPage.pageHeader.getText().contains("Book") );


    }
    List<String> listOfCategories;

    @And("the user clicks book categories")
    public void theUserClicksBookCategories() {
        BrowserUtil.waitFor(1);
        booksPage.mainCategoryElement.click();
       listOfCategories = booksPage.SelectAll();
       listOfCategories.remove(0);
    }

    @Then("verify book categories must match book_categories table from db")
    public void verifyBookCategoriesMustMatchBook_categoriesTableFromDb() {
       String query="select name from book_categories";
        DB_Util.runQuery(query);
        List<String> dbList=DB_Util.getColumnDataAsList(1);
       Assert.assertEquals(dbList, listOfCategories);

    }
}
