package com.library.step_definitions;

import com.library.pages.BooksPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US06_StepDefs {
    BooksPage booksPage=new BooksPage();
    @When("the librarian click to add book")
    public void theLibrarianClickToAddBook() {
        BrowserUtil.waitFor(1);
        booksPage.addBook.click();
    }

    @And("the librarian enter book name {string}")
    public void theLibrarianEnterBookName(String bName) {
        BrowserUtil.waitFor(1);
        booksPage.bookName.sendKeys(bName);
    }

    @When("the librarian enter ISBN {string}")
    public void theLibrarianEnterISBN(String isbn) {
        BrowserUtil.waitFor(1);
        booksPage.isbn.sendKeys(isbn);
    }

    @And("the librarian enter year {string}")
    public void theLibrarianEnterYear(String year) {
        BrowserUtil.waitFor(1);
        booksPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void theLibrarianEnterAuthor(String author) {
        BrowserUtil.waitFor(1);
        booksPage.author.sendKeys(author);
    }

    @And("the librarian choose the book category {string}")
    public void theLibrarianChooseTheBookCategory(String bCategory) {
        BrowserUtil.waitFor(1);
        Select select=new Select(booksPage.categoryDropdown);
        select.selectByVisibleText(bCategory);
    }

    @And("the librarian click to save changes")
    public void theLibrarianClickToSaveChanges() {
        BrowserUtil.waitFor(1);
        booksPage.saveChanges.click();
    }

    @Then("verify {string} message is displayed")
    public void verifyMessageIsDisplayed(String message) {
        BrowserUtil.waitFor(1);
        Assert.assertEquals(message, booksPage.toastMessage.getText());
    }

    @And("verify {string} information must match with DB")
    public void verifyInformationMustMatchWithDB(String bName) {
       String query="select name from books\n" +
               "where name in ('"+bName+"')\n" +
               "group by name";
        DB_Util.runQuery(query);
        Assert.assertEquals(DB_Util.getFirstRowFirstColumn(), bName);
    }
}
