Feature: View property projects in Kolkata
  As a user
  I want to view property projects in Kolkata
  So that I can see developer details and project listings

  Scenario: Open Projects page and view developer and project details
    Given I open the MagicBricks property page for Kolkata
    When I click on the "Buy" heading
    And I click on "Projects in Kolkata" and switch to the new tab
    Then I should see the "new-projects" page loaded
    And I print all developer details from featured projects
    When I click on the "Search Property" button
    Then I print all project listings