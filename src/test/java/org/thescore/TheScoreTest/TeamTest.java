package org.thescore.TheScoreTest;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


/**
 * End to end Test to Select favourites, find Team and navigate through tabs.
 */
public class TeamTest extends BaseTest {
		

	  @Test 
	  public void FindTeam() throws MalformedURLException {
	  
	    	//Replace path below with location of NPM in your system
	    	//Start Appium Programmatically 
	    	AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\bungh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
	    			.withIPAddress("127.0.0.1").usingPort(4723).build();
			service.start();

	    	
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("AshDevice");
			options.setApp("C:\\Users\\bungh\\eclipse-workspace\\TheScore\\TheScoreTest\\src\\test\\resources\\theScore.apk");
	        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // implicit wait for elements to appear within n seconds otherwise timeout
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("autoAcceptAlerts", true);
	  
		//Begin TEST
	        //WELCOME PAGE
	        
	        // Verify welcome text
			String welcome = driver.findElement(By.id("com.fivemobile.thescore:id/txt_welcome")).getAttribute("text");   
			Assert.assertEquals(welcome, "WELCOME");
			 // Click Get Started action button
			driver.findElement(By.id("com.fivemobile.thescore:id/action_button_text")).click();      
	       //Scroll into view
			//android ui automator uses Google engine, create class of ui scrollable and use the method scrollintoview
			//use resource id of widget to scroll into, 
			
			//CHOOSE YOUR FAVORITE LEAGUE PAGE

			Assert.assertTrue(driver.findElement(By.id("com.fivemobile.thescore:id/title_onboarding")).getText().contains("Choose your favorite leagues"));		      
			 // Scroll to find favourite league - Germany Soccer

			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.fivemobile.thescore:id/viewPager\")).scrollIntoView(text(\"Germany Soccer\"));"));		
	        
			//CHOOSE YOUR FAVORITE TEAMS PAGE
			
			// Click on Favourite Team = Germany Soccer
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.fivemobile.thescore:id/txt_name\" and @text=\"Germany Soccer\"]")).click();
			/* Create an instance of ScrollUtility
	        ScrollUtility scrollUtility = new ScrollUtility(driver);

	        // Call the method to scroll to the element with the specified text within the specified resource ID
	        WebElement element = scrollUtility.scrollIntoViewByResourceIdAndText("com.fivemobile.thescore:id/viewPager", "Germany Soccer");
	 		*/
			driver.findElement(By.id("com.fivemobile.thescore:id/action_button_text")).click();     		
			// Handle alerts
			driver.findElement(By.id("com.fivemobile.thescore:id/btn_disallow")).click();      

			//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.fivemobile.thescore:id/tabLayout\")).scrollIntoView(text(\"Germany\"));"));		
			driver.findElement(AppiumBy.androidUIAutomator(
				    "new UiScrollable(new UiSelector().resourceId(\"com.fivemobile.thescore:id/tabLayout\"))"
				    + ".setAsHorizontalList().scrollIntoView(new UiSelector().text(\"Germany\"));"));
			driver.findElement(By.xpath("//android.widget.TextView[@text=\"GERMANY\"]")).click();
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.fivemobile.thescore:id/txt_name\" and @text=\"Bayern Munich\"]")).click();
			driver.findElement(By.id("com.fivemobile.thescore:id/action_button_text")).click();     
			
			Assert.assertTrue(driver.findElement(By.id("com.fivemobile.thescore:id/title_onboarding")).getText().contains("Never miss a game"));		      

			driver.findElement(By.id("com.fivemobile.thescore:id/action_button_text")).click();      
			
			// Handle notifications alerts
			driver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button")).click();  
			
			//Handle The score bet alerts
			driver.findElement(By.id("com.fivemobile.thescore:id/dismiss_modal")).click();  
			
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
