Feature: Filter and search property projects in Kolkata
  As a user
  I want to filter property types in Kolkata projects
  So that I can view specific property types in search results

  Scenario: Select property type filter and search
    Given I open the MagicBricks property page for Kolkata
    When I click on the "Buy" heading
    And I click on "Projects in Kolkata" and switch to the new tab
    Then I should see the "new-projects" page loaded
    When I open the property type filter
    And I select the specific property type
    And I click on the "Search Property" button
    Then I print the selected property type from the results