package org.thescore.pageObjects.android;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.thescore.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Common extends AndroidActions{
	
	 AndroidDriver driver;
	
	//constructor to use the android driver from test case. one time set up. 
	public Common(AndroidDriver driver)
	{
		super(driver);
		// Refers to current class variables.
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);   //contructs selenium driver.findElements behind the scenes to use in test cases
	}

	//***ELEMENTS AND LOCATORS **** //

	@AndroidFindBy(id="com.fivemobile.thescore:id/dismiss_modal")
	private WebElement dismissModal;    // elements are marked as private so as to not use them in test cases, instead test cases must use public methods below.

	@AndroidFindBy(id="com.fivemobile.thescore:id/dialog_container")
	private WebElement overlayModal;
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/search_bar_text_view")
	private WebElement searchBar;
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/search_src_text")
	private WebElement searchField;
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/viewPager")
	private WebElement searchResults;
	

	//Method to close modal if it is displayed
	public void closeModal()
	{
        if (overlayModal.isDisplayed()) {
            dismissModal.click();
        } else {
            System.out.println("Modal not present or not clickable.");
        }
	}	
	
	
	/**
	 * Searches for a team using the specified search query and swipes through the search results.
	 * Clicks on the specified team element.
	 */
	public void searchAndSwipe(String team)
	{
		searchBar.click();
		searchField.sendKeys(team);
		driver.hideKeyboard();
	    WebElement searchResult = driver.findElement(By.id("com.fivemobile.thescore:id/viewPager"));

		swipeAction(searchResult, "left");
		
		 // Wait for the team element to be clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    WebElement teamElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@resource-id='com.fivemobile.thescore:id/txt_name' and @text='" + team + "']")));
	    
	    // Click on the team element
	    teamElement.click();
		
	}

    
}
