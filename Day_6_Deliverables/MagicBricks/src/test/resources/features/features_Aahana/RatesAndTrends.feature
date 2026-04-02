Feature: MagicBricks Property Rates and Trends

  Scenario: View property rates and trends for Hyderabad locality

    Given the user is on the MagicBricks Kolkata residential real estate page
    When the user clicks on the Rates and Trends option in the navigation menu
    And the user selects Hyderabad from the city list
    And the user clicks on View Trends for the first property
    And the user clicks on Locality Snapshot
    Then the locality details should be printed