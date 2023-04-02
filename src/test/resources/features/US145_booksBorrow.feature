@smoke @B28G1-154 @db
Feature: Borrowed Books data verification
  @B28G1-152 @ui
  Scenario: verify information from "Borrowed Books" module
    Given the user logged in as "librarian"
    When user get number of "Borrowed Books" module
    Then the number should be same with database
    @B28G1-153
  Scenario: verify the common book genre that’s being borrowed
    Given Establish the database connection
    When I execute query to find most popular book genre
    Then verify "Fantasy" is the most popular book genre.
