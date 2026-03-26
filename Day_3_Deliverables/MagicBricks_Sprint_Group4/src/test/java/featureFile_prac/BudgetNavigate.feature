Feature: Budget Filter - Navigate Budget Range from Property Detail Page
  As a property buyer in Kolkata
  I want to change the budget range from the property detail page
  So that I can explore properties in a different price range

  Background:
    Given I open the MagicBricks Kolkata residential real estate page

  Scenario: Navigate to a different budget range from property detail page
    When I click on the Budget filter option
    And I select minimum budget as "₹10 Lac"
    And I select the maximum budget option
    And I click the Search button
    And I click on the first property in the search results
    Then a new tab should open with the property detail page
    When I click on the "Buy" tag in the navigation bar
    And I click on the Budget option in the header
    And I select the budget range "₹ 1 Cr - ₹ 1.5 Cr"
    Then a new tab should open for the selected budget range
