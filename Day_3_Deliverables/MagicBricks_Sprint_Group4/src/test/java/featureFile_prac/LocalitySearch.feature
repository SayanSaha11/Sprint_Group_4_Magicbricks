Feature: Locality Search - Select and Explore
  As a property buyer in Kolkata
  I want to search for a locality, select it, and explore properties
  So that I can find properties in a specific area

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    And I click on the "Buy" heading

  Scenario: Select first autosuggestion and verify locality card
    Given I navigate to the Localities in Kolkata page
    When I click on the locality search input field
    And I type a locality name from the test data sheet
    And I select the first suggestion from the autosuggestion list
    Then the locality card title should be displayed

  Scenario: Explore properties in selected locality
    Given I navigate to the Localities in Kolkata page
    When I click on the locality search input field
    And I type a locality name from the test data sheet
    And I select the first suggestion from the autosuggestion list
    Then the locality card title should be displayed
    When I click the "Explore" button on the first locality card
    Then the properties for the selected locality should be displayed
