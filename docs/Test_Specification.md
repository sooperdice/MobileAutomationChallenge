## Test Specification Document

### Test Scenario: Onboarding User and Finding Team

#### Test Description:
This test scenario aims to verify the functionality of onboarding a user and finding a team within The Score mobile application.

#### Preconditions:
1. The application is installed and launched on the test device.
2. The user has not completed the onboarding process.

#### Test Steps:
1. Verify the welcome page text.
2. Select the sport and league.
3. Continue to the team selection page.
4. Handle location permission alert.
5. Choose a team from the list.
6. Continue to the notifications page.
7. Handle notifications permission alert.
8. Close any bet alerts.
9. Search for and select the desired team.
10. Verify that the team page is displayed correctly.
11. Tap on the player sub-tab.
12. Verify that the player stats tab is selected.
13. Verify that player stats headers are displayed.
14. Verify that the team name is correct.
15. Navigate back to the previous page.
16. Verify that the search field is present.

#### Expected Results:
- The welcome page should display the text "WELCOME".
- The user should be able to select a sport and league.
- The team selection page should be displayed.
- The location permission alert should be handled.
- The user should be able to choose a team from the list.
- The notifications page should be displayed.
- The notifications permission alert should be handled.
- Any bet alerts should be closed.
- The selected team page should be displayed with the correct team name.
- The player sub-tab should be selected.
- Player stats headers should be displayed.
- The team name should be correct on the player sub-tab.
- Navigating back should return the user to the previous page.
- The search field should be present on the previous page.

#### Test Data:
- Test data for selecting sport, league, and team will be provided via a data provider method.

#### Test Environment:
- Android device emulator- Pixel 6 using Android version 13.
- Appium for test automation.
- TestNG for test execution.


### Test Execution and Reporting:
- The test will be executed using TestNG framework.
- Test results and logs will be captured using ExtentReports for reporting and analysis.

### Additional Notes:
- This test scenario includes positive path testing for onboarding and team selection.
- A bug demonstration test is included to showcase a known issue in the application.
- The test is parameterized to accommodate different sport, league, and team selections.
- Custom logging is implemented to provide detailed information about test steps and outcomes.
- TestNG listeners are utilized for test execution monitoring and reporting.