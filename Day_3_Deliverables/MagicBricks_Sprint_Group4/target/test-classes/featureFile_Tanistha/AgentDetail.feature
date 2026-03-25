Feature: View agent details in Kolkata
  As a user
  I want to view real estate agents in Kolkata
  So that I can get their details

  Scenario: Open agent page and view first agent details
    Given I open the MagicBricks property page for Kolkata
    When I click on the "Buy" heading
    And I click on "Find an Agent" and switch to the new tab
    Then I should see the "agent-in-Kolkata" page loaded
    When I select the first agent and click "View Details"
    Then I should print the agent's name, company, and experience