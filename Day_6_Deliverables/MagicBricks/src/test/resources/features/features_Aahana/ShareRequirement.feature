Feature: Share Property Requirement on MagicBricks

  Scenario: User submits a property requirement through the Share Requirement form

    Given the user is on the MagicBricks Kolkata residential real estate page
    When the user clicks on the Post Property tab in the navigation menu
    And the user clicks on Share Requirement link
    And the user switches to the new window
    And the user selects property types as "Residential House" and "Multistorey Apartment"
    And the user selects budget as "70 - 80 Lac"
    And the user selects bedroom preference as "3 BHK"
    And the user selects floor preference as "Below 10th Floor"
    And the user sets area range from "1000" to "5000"
    And the user enters city as "Kolkata"
    And the user enters locality as "Rajarhat" and selects the first suggestion
    And the user accepts the Terms and Conditions
    And the user clicks the Submit button
    Then the requirement form should be submitted successfully