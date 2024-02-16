package org.thescore.pageObjects.android;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


//This POM Class contains the locators and action methods for Welcome page 

public class WelcomePage {
	
	AndroidDriver driver;
	
	//constructor to use the android driver from test case. one time set up. 
	public WelcomePage(AndroidDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);   //contructs selenium driver.findElements behind the scenes to use in test cases
	}
	
	//***ELEMENTS AND LOCATORS **** //

	@AndroidFindBy(id="com.fivemobile.thescore:id/txt_welcome")
	private WebElement welcomeTitle;    // elements are marked as private so as to not use them in test cases, instead test cases must use public methods below.

	@AndroidFindBy(id="com.fivemobile.thescore:id/action_button_text")
	private WebElement getStartedButton;
   
	
	//**** ACTION METHODS ***** //
	
	//The method resets the app to start page
	public void setActivity()
	{
			((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","com.fivemobile.thescore/com.fivemobile.thescore.ui.MainActivity"));			
			driver.activateApp("com.fivemobile.thescore");

	}

	
	public ChooseLeaguePage getStarted()
	{
		getStartedButton.click();
		// Logging after interacting with the welcome page element
		System.out.println("Clicked on the 'Get Started' button.");
		return new ChooseLeaguePage(driver); // smart way to create object for ChooseLeague page since we know it will always appear after clicking get started
	}	

    public String getWelcomeText() 
    {
        return welcomeTitle.getAttribute("text");
    }
    
}
