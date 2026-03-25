Feature: Locality Search and Auto-Suggestions on MagicBricks

  Scenario: View locality suggestions for a search input
    Given User opens MagicBricks website
    When User navigates to Localities in Kolkata page
    And User clicks on locality search input field
    And User enters locality name "Gariahat"
    Then User should see list of locality suggestions