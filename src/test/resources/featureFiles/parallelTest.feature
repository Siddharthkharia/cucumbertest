Feature: to test the cucumber feature file

  Scenario: TC04_to test passing data table step definitions
    Given Initialize and launch "https://www.amazon.in"
    And take screenshot of the webpage
    And search for the data of products
    |iphone|


