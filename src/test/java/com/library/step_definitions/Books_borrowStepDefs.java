package com.library.step_definitions;

import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Books_borrowStepDefs {
    String expectedGenre;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
      DB_Util.createConnection(ConfigurationReader.getProperty("library2.db.url"),ConfigurationReader.getProperty("library2.db.username"),ConfigurationReader.getProperty("library2.db.password"));
//DB_Util.createConnection();
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
        System.out.println(expectedGenre);
    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String genre) {
        Assert.assertEquals(expectedGenre,genre);
    }

    @Given("the user logged in as {string}")
    public void theUserLoggedInAs(String arg0) {
    }

    @When("user get number of {string} module")
    public void userGetNumberOfModule(String arg0) {
    }

    @Then("the number should be same with database")
    public void theNumberShouldBeSameWithDatabase() {
    }
}
