Feature: Find an Agent - Contact Agent
  As a property buyer in Kolkata
  I want to contact an agent directly
  So that I can inquire about properties

  Background:
    Given I open the MagicBricks Kolkata residential real estate page
    And I click on the "Buy" heading

  Scenario: Fill and submit the contact agent form
    When I click on "Find an Agent" link
    And a new tab opens with the agent listing
    And I click "Contact Agent" on the first agent card
    Then the contact form should be displayed
    When I enter the name from the test data sheet
    And I enter the email from the test data sheet
    And I enter the mobile number from the test data sheet
    And I click the submit button
    Then I complete the OTP verification