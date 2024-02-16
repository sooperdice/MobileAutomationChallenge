package org.thescore.TheScoreTest;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.thescore.TestUtils.BaseTest;
import org.thescore.TestUtils.ExtentReporterNG;
import org.thescore.TestUtils.Retry;
import org.thescore.pageObjects.android.ChooseLeaguePage;
import org.thescore.pageObjects.android.ChooseNotificationsPage;
import org.thescore.pageObjects.android.ChooseTeamPage;
import org.thescore.pageObjects.android.Common;
import org.thescore.pageObjects.android.FavoritesPage;
import org.thescore.pageObjects.android.TeamPage;
import org.thescore.utils.AndroidActions;



public class MainTest extends BaseTest {
	
	SoftAssert softAssert = new SoftAssert();

	/**
	 *	Test to Select favorites, find Team and navigate through tabs.
	 */
	@Test(dataProvider="getData",retryAnalyzer=Retry.class, description = "Test to Select favorites, find Team and navigate through tabs.")
	public void TC01_OnboardUserandFindTeam(HashMap<String, String> input) {
        // Initialization

		//WelcomePage welcomePage = new WelcomePage(driver);
		//ChooseLeaguePage chooseLeaguePage = new ChooseLeaguePage(driver);
		ChooseTeamPage chooseTeamPage = new ChooseTeamPage(driver);
		AndroidActions androidActions = new AndroidActions(driver);
		ChooseNotificationsPage chooseNotificationsPage = new ChooseNotificationsPage(driver);
		Common common = new Common(driver);
		FavoritesPage favoritesPage = new FavoritesPage(driver);
		TeamPage teamPage = new TeamPage(driver);

        // Onboard user and select team
		Reporter.log("Validate app welcome page text...");
		Assert.assertEquals(welcomePage.getWelcomeText(), "WELCOME", "Welcome text is incorrect");
		ChooseLeaguePage chooseLeaguePage = welcomePage.getStarted();

		chooseLeaguePage.chooseLeague2(input.get("sport"));
		chooseLeaguePage.continueToTeamPage();
		androidActions.handleLocationPermissionAlert();
		chooseTeamPage.chooseLeagueandTeam2(input.get("league"), input.get("team"));
		chooseTeamPage.continueButton();
		chooseNotificationsPage.doneButton();
		androidActions.handleNotificationsPermissionAlert();
		//	Handle The score bet alerts
		common.closeModal();
		Reporter.log("<h4>Completed the score onboarding flow</h4>");

		Reporter.log("1.Find and select team");
		favoritesPage.searchAndSelect(input.get("team"));
		Reporter.log("2.Verify that the team page opens correctly.");
		Assert.assertEquals(teamPage.getTeamNameText(), input.get("team"), ("Team name is incorrect"));
		Reporter.log("<h4>Team name verification passed successfully.</h4>");
		Reporter.log("3.Tap on  player sub-tab "); 
		teamPage.selectPlayerSubTab();
		Reporter.log("4.Verify correct tab and data displayed correctly");		
		Assert.assertTrue(teamPage.isTabSelected(teamPage.getPlayerStatsTab()), "Player stats tab is not selected");
		Reporter.log("<h4>Player Tab displayed successfully.</h4>");
		Assert.assertTrue(teamPage.verifyPlayerStatsHeaders(), "Player stats tab header is not displayed");
		Assert.assertEquals(teamPage.getTeamNameText(), input.get("team"),"Team name is incorrect");
        Reporter.log("5.Verify that back navigation returns to the previous page correctly.");
		driver.navigate().back();		
		Assert.assertTrue(favoritesPage.isSearchFieldDisplayed(), "Search field is not present");
		Reporter.log("<h4>Navigated to previous page succeessfully</h4>");
        System.out.println("Test Completed");        

	}
	
	
	/**
	 *	Test to demonstrate bug i found in the app related to refreshing results
	 *  Will fail unless fixed by the time you run this :)
	 */
	@Test(description = "Test to demonstrate a bug in the app related to refreshing results")
	public void TC02_RefreshResultsBug() {
		ChooseTeamPage chooseTeamPage = new ChooseTeamPage(driver);
		AndroidActions androidActions = new AndroidActions(driver);
		ChooseLeaguePage chooseLeaguePage = welcomePage.getStarted();
		chooseLeaguePage.continueToTeamPage();
		androidActions.handleLocationPermissionAlert();
        // Perform action that triggers the bug
		chooseTeamPage.searchAndhandleError();
	}
	
	
//	@BeforeMethod(alwaysRun = true)
//    // This method resets the app activity page to start before each test method
//	public void preSetup()
//	{
//		welcomePage.setActivity();
//	}
	

	@DataProvider
	public Object[][] getData() throws IOException 
	{	
		// Load test data from json file
		List<HashMap<String, String>> data = getJsonData((System.getProperty("user.dir")+"\\src\\test\\java\\org\\thescore\\testData\\leagueAndTeams.json"));
		
        return data.stream().map(entry -> new Object[]{entry}).toArray(Object[][]::new);	

	}
	 	
	 
}
