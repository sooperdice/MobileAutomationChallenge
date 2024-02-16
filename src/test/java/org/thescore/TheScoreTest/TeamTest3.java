package org.thescore.TheScoreTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.thescore.TestUtils.BaseTest;
import org.thescore.pageObjects.android.ChooseLeaguePage;
import org.thescore.pageObjects.android.ChooseNotificationsPage;
import org.thescore.pageObjects.android.ChooseTeamPage;
import org.thescore.pageObjects.android.Common;
import org.thescore.pageObjects.android.FavoritesPage;
import org.thescore.pageObjects.android.TeamPage;
import org.thescore.pageObjects.android.WelcomePage;
import org.thescore.utils.AndroidActions;

import com.google.common.collect.ImmutableMap;

import java.net.MalformedURLException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

/**
 * Test to find Team and navigate through tabs.
 */
public class TeamTest3 extends BaseTest {
	

	  @Test 
	  public void FindTeam() throws MalformedURLException {
	  
	  
		//Begin TEST
	        //FAvorites Page
	        
		  
		// Define the activity details
		  String appPackage = "com.fivemobile.thescore";
		  String appActivity = "com.fivemobile.thescore.ui.SplashActivity";
		  //String appActivity = "com.fivemobile.thescore.ui.MainActivity";

		  // Construct the intent
		  String intent = appPackage + "/" + appActivity;

		  // Start the activity
		  ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", intent));
			//Verify is on favorites page
			Assert.assertTrue(driver.findElement(By.id("com.fivemobile.thescore:id/navigation_bar_item_large_label_view")).isSelected());  

			//Search and Select Team
			driver.findElement(By.id("com.fivemobile.thescore:id/search_bar_text_view")).click();
			driver.findElement(By.id("com.fivemobile.thescore:id/search_src_text")).sendKeys("Bayern Munich");
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.fivemobile.thescore:id/txt_name\" and @text=\"Bayern Munich\"]")).click();
			//Select Tab
			driver.findElement(By.xpath("//android.widget.TextView[@text=\"PLAYER STATS\"]")).click();
			
	        // Verify Team page text
			String teamName = driver.findElement(By.id("com.fivemobile.thescore:id/team_name")).getAttribute("text");   
			Assert.assertEquals(teamName, "Bayern Munich");
	        driver.navigate().back();
	        Assert.assertTrue(driver.findElement(By.id("com.fivemobile.thescore:id/search_src_text")).isDisplayed());
	  
	  }
	

	 
}
