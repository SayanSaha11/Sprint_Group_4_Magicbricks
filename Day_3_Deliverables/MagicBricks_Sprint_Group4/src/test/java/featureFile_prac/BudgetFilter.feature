Feature: Budget Filter - Property Search and Description
  As a property buyer in Kolkata
  I want to filter properties by budget
  So that I can find properties within my price range

  Background:
    Given I open the MagicBricks Kolkata residential real estate page

  Scenario: Apply budget filter and view property description
    When I click on the Budget filter option
    And I select minimum budget as "₹10 Lac"
    And I select the maximum budget option
    And I click the Search button
    Then the search results page should display property listings

  Scenario: Open first property and read the description
    When I click on the Budget filter option
    And I select minimum budget as "₹10 Lac"
    And I select the maximum budget option
    And I click the Search button
    And I click on the first property in the search results
    Then a new tab should open with the property detail page
    And the page title should be displayed
    When I click on the "Read More" link in the description section
    Then the full property description should be visible and displayed
