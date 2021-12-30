Feature: Find Owner

  Scenario: find should be successful
    Given There is an owner with first name "John"
    And There is an owner with first name "Jack"
    When Using find owner service to find "John"
    Then Founded owner first name is "John"

  Scenario: find should not be successful
    Given There is an owner with first name "John"
    And There is an owner with first name "Jack"
    When Using find owner service to find "John"
    Then Founded owner first name is not "Jack"
