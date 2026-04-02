Feature: MagicBricks Home Interior Design Service Booking

  As a homeowner in Kolkata
  I want to book a Home Interior Design Service slot on MagicBricks
  So that I can get professional interior design assistance for my property

  Scenario: Successfully book a Home Interior Design Service slot

    Given I navigate to the MagicBricks Kolkata residential real estate page
    And the browser is launched with anti-automation detection settings
    When I click on the "Home Interior" tab in the nav menu
    And I click on the "Home Interior Design Services" link there
    And I switch to the newly opened window
    And I enter "Jane Doe" in the Name field
    And I enter "9874745674" in the Phone Number field
    And I click the "Book your Slot" button now
    And I click the "Submit" button to proceed
    And I wait for the timeline options to appear
    And I select "3-6 months" as the project timeline
    And I wait for the budget options to appear
    And I select "3-5 Lakhs" as the budget range
    And I click the "Submit" button to confirm
    Then the user should be redirected to the property details form
    And a consultation slot should be booked successfully