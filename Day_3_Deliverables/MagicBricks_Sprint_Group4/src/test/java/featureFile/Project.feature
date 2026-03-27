Feature: Projects in Kolkata - View, Browse and Filter Listings
  As a property buyer in Kolkata
  I want to view new projects, developer details, and filter by property type
  So that I can explore and narrow down upcoming residential projects

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    And I click on the "Buy" heading
    And I click on "Projects in Kolkata" link
    And the new projects page loads with URL containing "new-projects"

  Scenario: View featured developer details and browse project listings
    Then the featured developer listings should be displayed
    And the details of each developer should be printed
    When I click the Search button on the projects page
    Then the project search results should be displayed
    And the details of each project in the list should be printed

  Scenario: Apply property type filter and verify results
    When I click on the Property Type filter
    And I select a specific property type from the filter options
    And I click the Search button on the projects page
    Then the search results should be displayed with the applied filter
    And the selected property type should be shown in the filter display area