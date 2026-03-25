Feature: Search and explore locality in MagicBricks
  As a user
  I want to search for a locality in Kolkata and explore its details
  So that I can view property information for that locality

  Scenario: Search for Gariahat locality and explore it
    Given I open the MagicBricks property page for Kolkata
    When I click on the "Buy" heading
    And I navigate to the "Localities in Kolkata" page
    And I enter "Gariahat" in the locality search box
    And I select the first suggestion from the list
    Then I should see the title of the first locality card printed
    When I click on the "Explore" link of the first locality card