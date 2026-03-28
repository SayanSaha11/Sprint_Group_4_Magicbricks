Feature: MagicBricks Kolkata - Property Search, Projects, Localities and Agent Features


# ---------------------------------------------------
# 🔹 BUDGET FILTER (🔥 NO BUY STEP HERE)
# ---------------------------------------------------
Scenario: Apply budget filter and verify property listings are displayed
  Given I open the MagicBricks Kolkata residential real estate page
  When I click on the Budget filter option
  And I select minimum budget as "10 Lac"
  And I select the maximum budget option
  And I click the Search button
  Then the search results page should display property listings


# ---------------------------------------------------
# 🔹 PROJECTS (UI NAVIGATION → NO BUY STEP NEEDED)
# ---------------------------------------------------
Scenario: View featured developer details and browse project listings
  Given I open the MagicBricks Kolkata residential real estate page
  And I click on the "Buy" heading
  When I click on "Projects in Kolkata" link
  Then the new projects page loads with URL containing "new-projects"
  Then the featured developer listings should be displayed
  And the details of each developer should be printed
  When I click the Search button on the projects page
  Then the project search results should be displayed
  And the details of each project in the list should be printed

Scenario: Apply property type filter and verify results
  Given I open the MagicBricks Kolkata residential real estate page
  And I click on the "Buy" heading
  When I click on "Projects in Kolkata" link
  Then the new projects page loads with URL containing "new-projects"
  When I click on the Property Type filter
  And I select a specific property type from the filter options
  And I click the Search button on the projects page
  Then the search results should be displayed with the applied filter
  And the selected property type should be shown in the filter display area

# ---------------------------------------------------
# 🔹 PROPERTY DETAILS
# ---------------------------------------------------
Scenario: Open first property and read the full description
  Given I open the MagicBricks Kolkata residential real estate page
  When I click on the Budget filter option
  And I select minimum budget as "10 Lac"
  And I select the maximum budget option
  And I click the Search button
  Then the search results page should display property listings
  When I click on the first property in the search results
  And I switch to the property detail tab
  Then the page title should be displayed
  And the full property description should be visible and displayed
  
# ---------------------------------------------------
# 🔹 AGENT SCENARIO (Needs UI navigation)
# ---------------------------------------------------
Scenario: View details of the first listed agent
  Given I open the MagicBricks Kolkata residential real estate page
  And I click on the "Buy" heading
  When I click on "Find an Agent" link
  And I switch to the new agent listing tab
  And I click "View Details" on the first agent card
  And I switch to the agent detail tab
  Then the agent name should be displayed
  And the agent company or project name should be displayed
  And the agent experience details should be displayed

# ---------------------------------------------------
# 🔹 CONTACT AGENT FORM
# ---------------------------------------------------
Scenario: Fill and submit the contact agent form
  Given I open the MagicBricks Kolkata residential real estate page
  And I click on the "Buy" heading
  When I click on "Find an Agent" link
  And I switch to the new agent listing tab
  And I click "Contact Agent" on the first agent card
  Then the contact form should be displayed
  When I enter the name from the test data sheet
  And I enter the email from the test data sheet
  And I enter the mobile number from the test data sheet
  And I click the submit button
  Then I complete the OTP verification