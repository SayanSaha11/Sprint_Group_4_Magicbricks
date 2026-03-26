Feature: Find an Agent - View Agent Details
  As a property buyer in Kolkata
  I want to find and view agent details
  So that I can learn about agents available in my area

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    And I click on the "Buy" heading

  Scenario: Navigate to Find an Agent page and view first agent details
    When I click on "Find an Agent" link
    Then a new tab should open with URL containing "agent-in-Kolkata"
    And the agent listing page should display a list of agents

  Scenario: View details of the first listed agent
    When I click on "Find an Agent" link
    And a new tab opens with the agent listing
    And I click "View Details" on the first agent card
    Then a new tab should open with the agent detail page
    And the agent name should be displayed
    And the agent company or project name should be displayed
    And the agent experience details should be displayed
