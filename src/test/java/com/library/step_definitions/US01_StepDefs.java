package com.library.step_definitions;

import com.library.utility.DB_Util;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class US01_StepDefs {
    String numOfID;
    List<String> expectedColumns=new ArrayList<>();
  @Before
  public void setupDB(){
      System.out.println("Connecting to database...");
      DB_Util.createConnection();
  }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

       String query="select count(id) from users";
        DB_Util.runQuery(query);
        String actualNumOfID=DB_Util.getFirstRowFirstColumn();
        this.numOfID=actualNumOfID;
        System.out.println(actualNumOfID);
    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query="select count(distinct id) from users";
        DB_Util.runQuery(query);
        String expectedNumOfUniqueID=DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedNumOfUniqueID);
        Assert.assertEquals(expectedNumOfUniqueID,numOfID);

    }
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        String query="select * from users";
        DB_Util.runQuery(query);
        this.expectedColumns=DB_Util.getAllColumnNamesAsList();
        System.out.println(DB_Util.getAllColumnNamesAsList());
    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String>actualColumns) {
       Assert.assertEquals(expectedColumns,actualColumns);
    }
    @After
    public void closeDB(){
        System.out.println("Closing DB connection...");
        DB_Util.destroy();
    }
}
