Feature: to test the cucumber feature file

  Scenario: TC01_to test passing data table step definitions
    Given Initialize and launch "https://www.amazon.in"
    And take screenshot of the webpage
    And search for the data of products
    |ipad|
    |iphone|

    Scenario: TC02_To test the POM model of the amazon search page
      Given Execute the amazon launch and search operation for the "Scotch Brite"

  Scenario: TC03_to test passing single value step definitions
    Given Initialize and launch "https://www.amazon.in"
    And take screenshot of the webpage
    And search for the "Batman"

