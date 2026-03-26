Feature: MagicBricks Test Scenarios
  As a user
  I want to interact with MagicBricks website
  So that I can filter properties, explore localities, view projects, and contact agents

  @Budget
  Scenario: Apply budget filter and view property description
    Given I open the MagicBricks website for "Kolkata"
    When I apply budget filter from "10 Lac" to "X BHK"
    And I click the search button
    Then I should see the property listings
    When I open the first property
    And I read full property description

  @Locality
  Scenario: Search for Gariahat locality and explore it
    Given I open the MagicBricks website for "Kolkata"
    When I navigate to "Localities" page
    And I search for locality "Gariahat"
    And I select the first suggestion
    Then I should see the title of the first locality card
    When I explore the first locality card

  @Property
  Scenario: Select property type filter and search
    Given I open the MagicBricks website for "Kolkata"
    When I navigate to "Projects" page
    And I select property type "X"
    And I click the search button
    Then I should see the selected property type in results

  @Agent
  Scenario: Fill agent contact form and verify OTP
    Given I open the MagicBricks website for "Kolkata"
    When I navigate to "Find an Agent" page
    And I select the first agent and "Contact"
    And I fill contact form with name "Jane Doe", email "abcd123@gmail.com", mobile "6289992085"
    And I submit contact form
    Then I wait for OTP field
    And I enter OTP manually
    When I verify OTP