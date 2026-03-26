Feature: MagicBricks Kolkata Property Tests
  This feature tests various functionalities on MagicBricks website for Kolkata properties including budget search, locality search, projects, and agent interactions.

  ##########################################
  # Budget Filter & Property Details Tests #
  ##########################################

  Scenario: Search property by budget and view details
    Given I open the MagicBricks property page for "Kolkata"
    When I click on the "Budget" filter
    And I select "₹10 Lac" as the budget
    And I select maximum "8" BHK
    And I click on the "Search" button
    And I click on the first property in the search results
    And I switch to the new property tab
    Then I should see the property page title
    When I click on "Read More" in the description
    Then I should see the full property description

  Scenario: Navigate to a different budget and verify new tab opens
    Given I open the MagicBricks property page for "Kolkata"
    When I click on the "Budget" filter
    And I select "₹10 Lac" as the budget
    And I select maximum "8" BHK
    And I click on the "Search" button
    And I click on the first property in the search results
    And I switch to the new property tab
    Then I should see the property page title
    When I click on the header link to select another budget
    And I select budget "₹1 Cr - ₹1.5 Cr"
    Then a new tab should open

  ##########################################
  # Locality Search Tests                  #
  ##########################################

  Scenario: Verify locality autosuggestions in Kolkata
    Given I open the MagicBricks property page for "Kolkata"
    When I navigate to the "Localities in Kolkata" page
    And I click on the locality input field
    And I enter the locality from Excel
    Then I should see a list of suggested localities
    And I print all suggestions

  Scenario: Select a locality and explore it
    Given I open the MagicBricks property page for "Kolkata"
    When I navigate to the "Localities in Kolkata" page
    And I click on the locality input field
    And I enter the locality from Excel
    And I select the first suggested locality
    Then I should see the selected locality name
    When I click on the "Explore" link for the locality

  ##########################################
  # Projects in Kolkata Tests              #
  ##########################################

  Scenario: View projects in Kolkata and print details
    Given I open the MagicBricks property page for "Kolkata"
    When I click on the "Buy" heading
    And I click on "Projects in Kolkata" link
    And I switch to the new tab
    Then the "New Projects" page should load
    When I view all featured developer details
    Then I print all developer details
    When I click on "Property Search" button
    Then I print all project details in the results

  Scenario: Search projects by property type
    Given I open the MagicBricks property page for "Kolkata"
    When I click on the "Buy" heading
    And I click on "Projects in Kolkata" link
    And I switch to the new tab
    Then the "New Projects" page should load
    When I click on the "Property Type" filter
    And I select a specific property type
    And I click on "Property Search" button
    Then I should see the selected property type

  ##########################################
  # Agent Search & Contact Tests           #
  ##########################################

  Scenario: View agent details in Kolkata
    Given I open the MagicBricks property page for "Kolkata"
    When I click on the "Buy" heading
    And I click on "Find an Agent" link
    And I switch to the new tab
    Then the "Agent in Kolkata" page should load
    When I view all agents
    And I click on "View Details" for the first agent
    And I switch to the agent detail tab
    Then I should see the agent name, company, and experience

  Scenario: Contact an agent using the form and OTP
    Given I open the MagicBricks property page for "Kolkata"
    When I click on the "Buy" heading
    And I click on "Find an Agent" link
    And I switch to the new tab
    Then the "Agent in Kolkata" page should load
    When I click on "Contact Agent" for the first agent
    And I fill the contact form with name, email, and mobile from Excel
    And I click on "Submit" button
    Then the OTP field should appear
    When I enter OTP manually
    And I click on "Verify" button