@AutomationTest
Feature: Brand Data Excel Manipulation

  Scenario: Storing brand data from amazon and writing to excel
    Given Store brand data
    Then  write data to excel and save it
    Then  parse the excel data to a json file