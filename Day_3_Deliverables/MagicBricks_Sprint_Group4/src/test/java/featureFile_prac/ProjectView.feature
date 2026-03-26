Feature: Projects in Kolkata - View Developer and Project Listings
  As a property buyer in Kolkata
  I want to view new projects and developer details
  So that I can explore upcoming residential projects

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    And I click on the "Buy" heading

  Scenario: Navigate to Projects in Kolkata page
    When I click on "Projects in Kolkata" link
    Then a new tab should open with URL containing "new-projects"

  Scenario: View featured developer details on the Projects page
    When I click on "Projects in Kolkata" link
    And the new projects page loads
    Then the featured developer listings should be displayed
    And the details of each developer should be printed

  Scenario: Search and view all projects listing
    When I click on "Projects in Kolkata" link
    And the new projects page loads
    And I click the Search button on the projects page
    Then the project search results should be displayed
    And the details of each project in the list should be printed
