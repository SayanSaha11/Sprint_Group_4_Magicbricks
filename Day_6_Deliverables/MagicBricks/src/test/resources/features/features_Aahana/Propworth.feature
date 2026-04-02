Feature: MagicBricks PropWorth Property Estimation

  Scenario: User estimates property value for a Rajarhat locality in Kolkata

    Given the user is on the MagicBricks Kolkata residential page
    When the user navigates to PropWorth from the top menu
    And the user searches for locality "Rajarhat"
    And the user selects the first suggestion
    And the user clicks Get Estimate
    And the user selects property type as "Flat"
    And the user selects BHK as "1 BHK"
    And the user enters super area as "1500" square feet
    And the user adds "1" covered parking
    And the user selects property age as "1-3 years"
    And the user enters interior amount as "200000"
    And the user enters total number of floors as "6"
    And the user clicks the final Get Estimate button
    Then the user is redirected to the login page for credentials