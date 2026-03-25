Feature: Budget Filter and Property Details on MagicBricks

  Scenario: Apply budget filter and view property description
    Given User opens MagicBricks website
    When User selects budget filter option
    And User selects minimum price as 10 Lac
    And User selects maximum BHK option
    And User clicks on search button
    Then User should see list of properties

    When User selects the first property
    Then User should be navigated to property details page

    When User clicks on Read More description
    Then User should see full property description