Feature: Budget Navigation and New Tab Validation on MagicBricks

  Scenario: Apply budget filter and navigate to property page
    Given User opens MagicBricks website
    When User selects budget filter option
    And User selects minimum price as 10 Lac
    And User selects maximum BHK option
    And User clicks on search button
    Then User should see list of properties

    When User selects the first property
    Then User should be navigated to property details page

  Scenario: Verify new tab opens after selecting different budget
    Given User is on property details page
    When User clicks on budget navigation option
    And User selects price range 1 Cr to 1.5 Cr
    Then A new tab should be opened