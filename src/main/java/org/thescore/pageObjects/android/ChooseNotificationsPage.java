package org.thescore.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This POM Class contains the locators and action methods for Choose Notifications page 

public class ChooseNotificationsPage {
	
	AndroidDriver driver;
	
	//constructor to use the android driver from test case. one time set up. 
	public ChooseNotificationsPage(AndroidDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);   //contructs selenium driver.findElements behind the scenes to use in test cases
	}
	
	//***ELEMENTS AND LOCATORS **** //

	
	@AndroidFindBy(id="com.fivemobile.thescore:id/title_onboarding")
	private WebElement notificationTitle;    // elements are marked as private so as to not use them in test cases, instead test cases must use public methods below.
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/action_button_text")
	private WebElement doneButton;

	


    public String getNotifcationTitleText() 
    {
        return notificationTitle.getAttribute("text");
    }
    
    
	public void doneButton()
	{
		doneButton.click();
				
	}	
    
}
