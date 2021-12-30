Feature: Find Pet

  Background: Define a generic pet type for all pets
    Given There is pet type "animal"

  Scenario: find should be successful
    Given There is an owner with first name "John"
    And There is a pet called "Odie" with owner "John"
    And There is a pet called "Garfield" with owner "John"
    When Using find pet service to find "Garfield"
    Then Founded pet name is "Garfield"

  Scenario: find should not be successful
    Given There is an owner with first name "John"
    And There is a pet called "Odie" with owner "John"
    And There is a pet called "Garfield" with owner "John"
    When Using find pet service to find "Garfield"
    Then Founded pet name is not "Odie"
