Feature: MagicBricks Credit Score Check

  As a property buyer in Kolkata
  I want to check my Credit Score through MagicBricks
  So that I can understand my loan eligibility before applying

  Scenario: User successfully submits the Credit Score form and verifies the report

    Given the user is on the MagicBricks Kolkata residential real estate page
    When the user clicks on the Financial Services tab in the navigation menu
    And the user clicks on the Check Credit Score link
    And the user switches to the new Credit Score window
    And the user enters first name as "John"
    And the user enters last name as "Doe"
    And the user enters email address as "johndoe@example.com"
    And the user enters mobile number as "9874745674"
    And the user selects the gender option
    And the user ticks the Terms and Conditions button
    And the user clicks on Get Free Report
    Then the user should be able to verify the credit score report