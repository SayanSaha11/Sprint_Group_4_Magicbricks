Feature: Locality Search - Autosuggestion
  As a property buyer in Kolkata
  I want to search for a locality and see autosuggestions
  So that I can quickly find the locality I am looking for

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    And I click on the "Buy" heading

  Scenario: Search locality input shows autosuggestions
    Given I navigate to the Localities in Kolkata page
    When I click on the locality search input field
    And I type a locality name from the test data sheet
    Then the autosuggestion dropdown should appear
    And the suggestions list should contain relevant locality results

  Scenario: Autosuggestion list displays all matching results
    Given I navigate to the Localities in Kolkata page
    When I click on the locality search input field
    And I type a locality name from the test data sheet
    Then each suggestion in the autosuggestion list should be printed and visible
