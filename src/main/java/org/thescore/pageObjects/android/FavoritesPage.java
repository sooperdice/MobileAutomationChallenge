package org.thescore.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


//This POM Class contains the locators and action methods for Favorites page 

public class FavoritesPage {
	
	AndroidDriver driver;
	
	//constructor to use the android driver from test case. one time set up. 
	public FavoritesPage(AndroidDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);   //contructs selenium driver.findElements behind the scenes to use in test cases
	}
	
	//***ELEMENTS AND LOCATORS **** //

	
	@AndroidFindBy(id="com.fivemobile.thescore:id/title_onboarding")
	private WebElement notificationTitle;    // elements are marked as private so as to not use them in test cases, instead test cases must use public methods below.
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/navigation_bar_item_large_label_view")
	private WebElement favoritesButton;

	@AndroidFindBy(id="com.fivemobile.thescore:id/search_bar_text_view")
	private WebElement searchBar;
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/search_src_text")
	private WebElement searchField;


    public String getNotifcationTitleText() 
    {
        return notificationTitle.getAttribute("text");
    }
    
    
	public void selectFavoritesTab()
	{
		favoritesButton.click();	
	}	
	
	public void searchAndSelect(String team)
	{
		searchBar.click();
		searchField.sendKeys(team);
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.fivemobile.thescore:id/txt_name\" and @text=\"" + team +"\"]")).click();
		
	}
	
    public boolean isSearchFieldDisplayed() {
        return searchField.isDisplayed();
    }
    
}
