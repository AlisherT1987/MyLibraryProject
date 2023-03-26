package com.library.step_definitions;

import com.library.pages.DashboardPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_StepDefs {
    DashboardPage dashboard=new DashboardPage();
    LoginPage login=new LoginPage();
    String bbNum;
    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {
        login.login(userType);

    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        System.out.println(dashboard.borrowedBooksNumber.getText());
        this.bbNum=dashboard.borrowedBooksNumber.getText();

    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        String query="select count(*) from book_borrow\n" +
                "where is_returned=0";
        DB_Util.runQuery(query);
        String expectedBBNum=DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBBNum,bbNum);

    }
}
