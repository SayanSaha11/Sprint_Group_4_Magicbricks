Feature: MagicBricks Developer Lounge - Developer Insight Video Playback

  Scenario: User plays the third insight video from Mohit Goel's Developer Lounge profile

    Given the user is on the MagicBricks Kolkata residential real estate page
    When the user navigates to the "Tools & Advice" section
    And the user clicks on "Developer Lounge"
    And the user clicks on Mohit Goel's profile circle
    And the user clicks on the third insight card
    Then the video should start playing