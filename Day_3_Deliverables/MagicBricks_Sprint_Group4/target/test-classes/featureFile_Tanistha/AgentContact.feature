Feature: Contact a real estate agent in Kolkata
  As a user
  I want to contact a real estate agent
  So that I can inquire about properties

  Scenario: Fill agent contact form and verify OTP
    Given I open the MagicBricks property page for Kolkata
    When I click on the "Buy" heading
    And I click on "Find an Agent" and switch to the new tab
    Then I should see the "agent-in-Kolkata" page loaded
    When I select the first agent and click "Contact Agent"
    And I fill in the contact form with name "Jane Doe", email "abcd123@gmail.com", and mobile "6289992085"
    And I click the "Contact" button
    Then I wait for the OTP field to appear
    And I manually enter the OTP
    When I click the "Verify" button