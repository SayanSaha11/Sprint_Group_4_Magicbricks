Feature: MagicBricks Home Loan Application

  As a property buyer in Kolkata
  I want to apply for a Home Loan through MagicBricks
  So that I can get loan offers and be contacted by lending partners

  Scenario: Successfully submit a Home Loan application with OTP verification

    Given I navigate to the MagicBricks Kolkata residential real estate page
    And the browser is launched with anti-automation detection settings
    When I click on the "More Services" tab in the navigation menu
    And I click on the "Home Loans" link
    And I switch to the newly opened Home Loans window
    And I enter "5000000" in the Loan Amount field
    And I enter "9874745674" in the Mobile Number field
    And I enter "Kolkata" in the Property City field
    And I wait for the city dropdown suggestions to appear
    And I select the first city suggestion from the dropdown
    And I select the second option for loan type preference
    And I select the fifth option for loan tenure preference
    And I click the "Generate OTP" button
    Then a confirmation message should be displayed on the screen
    And an OTP should be sent to the registered mobile number "9874745674"
    When I manually enter the OTP within 30 seconds
    Then the application should be submitted successfully
    And I should receive a callback confirmation message
