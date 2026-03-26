Feature: Projects in Kolkata - Filter by Property Type
  As a property buyer in Kolkata
  I want to filter projects by property type
  So that I can narrow down the results to my preferred property type

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    And I click on the "Buy" heading

  Scenario: Apply property type filter on the Projects page
    When I click on "Projects in Kolkata" link
    And the new projects page loads with URL containing "new-projects"
    And I click on the Property Type filter
    And I select a specific property type from the filter options
    And I click the Search button on the projects page
    Then the search results should be displayed with the applied filter
    And the selected property type should be shown in the filter display area
