Feature: MagicBricks Kolkata - Property Search, Projects, Localities and Agent Features

Scenario: Verify autosuggestions and locality card on search
  Given I open the MagicBricks Kolkata residential real estate page
  And I click on the "Buy" heading
  And I navigate to the Localities in Kolkata page
  And I click on the locality search input field
  And I type a locality name from the test data sheet
  Then the autosuggestion dropdown should appear
  And the suggestions list should contain relevant locality results
  And each suggestion in the autosuggestion list should be printed and visible

Scenario: Explore properties in selected locality
  Given I open the MagicBricks Kolkata residential real estate page
  And I click on the "Buy" heading
  And I navigate to the Localities in Kolkata page
  And I click on the locality search input field
  And I type a locality name from the test data sheet
  When I select the first suggestion from the autosuggestion list
  Then the locality card title should be displayed
  When I click the "Explore" button on the first locality card
  Then the properties for the selected locality should be displayed