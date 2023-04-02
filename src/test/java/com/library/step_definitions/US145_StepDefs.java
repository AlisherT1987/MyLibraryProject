package com.library.step_definitions;

import com.library.pages.DashboardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US145_StepDefs {
    LoginPage loginPage=new LoginPage();
    DashboardPage dashboardPage=new DashboardPage();
    String expectedGenre;
    String numberOfBB;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {

    }
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        String query="select book_categories.name,count(book_category_id) as \"Total amount\" from books left outer join book_borrow bb on books.id = bb.book_id left outer join book_categories on books.book_category_id = book_categories.id\n" +
                "where  is_returned=0\n" +
                "group by book_categories.name\n" +
                "order by 2 desc";
        DB_Util.runQuery(query);
        String expectedResult=DB_Util.getFirstRowFirstColumn();
        this.expectedGenre=expectedResult;

    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String genre) {
        Assert.assertEquals(expectedGenre,genre);
    }

    @Given("the user logged in as {string}")
    public void theUserLoggedInAs(String userType) {
        loginPage.login(userType);

    }

    @When("user get number of {string} module")
    public void userGetNumberOfModule(String module) {
        BrowserUtil.waitFor(2);
        Assert.assertEquals(module, dashboardPage.borrowedBooksModule.getText());
    }

    @Then("the number should be same with database")
    public void theNumberShouldBeSameWithDatabase() {
        String query="select count(*) from book_borrow\n" +
                "where is_returned=0";
        DB_Util.runQuery(query);
        Assert.assertEquals(DB_Util.getFirstRowFirstColumn(),dashboardPage.borrowedBooksNumber.getText());

    }
}

/*
Scenario: verify the common book genre that’s being borrowed
    Given Establish the database connection
    When I execute query to find most popular book genre
    Then verify "Action and Adventure" is the most popular book genre.


select bc.name,count(*) from book_borrow bb
    inner  join books b on bb.book_id = b.id
    inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;
 */