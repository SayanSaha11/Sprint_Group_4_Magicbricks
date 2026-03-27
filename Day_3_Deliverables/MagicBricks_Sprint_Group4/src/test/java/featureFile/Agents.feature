Feature: Find an Agent - View Details and Contact Agent
  As a property buyer in Kolkata
  I want to find, view, and contact agents
  So that I can learn about and reach out to agents available in my area

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    And I click on the "Buy" heading
    When I click on "Find an Agent" link
    And I switch to the new agent listing tab
	
  Scenario: View details of the first listed agent
    When I click "View Details" on the first agent card
    And I switch to the agent detail tab
    Then the agent name should be displayed
    And the agent company or project name should be displayed
    And the agent experience details should be displayed

  Scenario: Fill and submit the contact agent form
    When I click "Contact Agent" on the first agent card
    Then the contact form should be displayed
    When I enter the name from the test data sheet
    And I enter the email from the test data sheet
    And I enter the mobile number from the test data sheet
    And I click the submit button
    Then I complete the OTP verification