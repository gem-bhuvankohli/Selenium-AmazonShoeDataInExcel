@AutomationTest
Feature: Amazon Product Search Automation

  Scenario: Open and search amazon.in for shoes
    Given Open amazon.in
    Then  Search for shoes

  Scenario: Filtering adidas and puma brand shoes
    Given Filter brand shoes

