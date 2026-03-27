Feature: Budget Filter - Property Search, Description and Navigation
  As a property buyer in Kolkata
  I want to filter properties by budget and explore them
  So that I can find and navigate properties within my price range

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    When I click on the Budget filter option
    And I select minimum budget as "₹10 Lac"
    And I select the maximum budget option
    And I click the Search button

  Scenario: Apply budget filter and verify property listings are displayed
    Then the search results page should display property listings

  Scenario: Open first property and read the full description
    When I click on the first property in the search results
    And I switch to the property detail tab
    And I click on the "Read More" link in the description section
    Then the page title should be displayed
    And the full property description should be visible and displayed
