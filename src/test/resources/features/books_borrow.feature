@db
Feature: Borrowed Books data verification
  Scenario: verify information from "Borrowed Books" module
    Given the user logged in as "librarian"
    When user get number of "Borrowed Books" module
    Then the number should be same with database
  Scenario: verify the common book genre that’s being borrowed
    Given Establish the database connection
    When I execute query to find most popular book genre
    Then verify "Fantasy" is the most popular book genre.
