Feature: MagicBricks Rent Page Tests

  # TC_02 - Positive
  @TC_02
  Scenario: Auto-suggest appears when user types a valid city
    Given user opens MagicBricks and goes to Rent tab
    When user types "Kolkata" and selects from dropdown
    And user clicks search button
    Then search results should be displayed

	# TC_04 - Negative
	@TC_04
	Scenario Outline: Error message shown for invalid location input
	  Given user opens MagicBricks and goes to Rent tab
	  When user types invalid city using test data "<id>"
	  And user clicks search button
	  Then an error message should be displayed
	
	Examples:
	  | id |
	  | 1  |


  # TC_26 - Positive
  @TC_26
  Scenario: Properties are sorted in ascending order of price
    Given user is on MagicBricks Rent search results page
    When user applies locality filter New Town and Posted By Owners
    And user selects Sort by Price Low to High
    Then listings should be displayed in ascending order of price

  # TC_29 - Positive
  @TC_29
  Scenario: User can view full property details
    Given user searches for properties in Kolkata on Rent page
    When user clicks on the first property listing
    Then full property details should be displayed

  # TC_30 - Positive
  @TC_30
  Scenario: User can view agent name photo contact and listing history
    Given user is on Rent search results with New Town filter applied
    When user clicks Top Agents and views first agent details
    Then agent name photo contact number and listing history should be displayed

  # TC_31 - Positive
  @TC_31
  Scenario: User can view location details on property page
    Given user searches properties in Kolkata for location details
    When user clicks on first property to view location
    Then location details should contain Kolkata

	# TC_34 - Positive
	@TC_34
	Scenario Outline: User can contact agent by verifying OTP
	  Given user searches properties in Kolkata to contact agent
	  When user applies New Town filter and clicks Contact Owner
	  And user fills contact form using test data "<id>"
	  And user waits and enters OTP manually
	  Then user clicks verify and contact details should be accessed
	
	Examples:
	  | id |
	  | 1  |

	# TC_35 - Positive
	@TC_35
	Scenario Outline: User can write and submit a property review
	  Given user searches properties in Kolkata for review
	  When user clicks on a property and writes a review using test data "<id>"
	  Then the review should be submitted successfully
	
	Examples:
	  | id |
	  | 1  |
