package org.thescore.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.thescore.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ChooseLeaguePage extends AndroidActions{
	
	AndroidDriver driver;
	
	//Constructor to use the android driver from test case. 
	public ChooseLeaguePage(AndroidDriver driver)
	{
		//super calls parent class constructor
		super(driver);
		// Refers to current class variables.
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);   //contructs selenium driver.findElements behind the scenes to use in test cases
	}
	
	//***ELEMENTS AND LOCATORS **** //

	
	// Elements are marked as private so as to encapsulate them in page object. 
	// Prevents direct access by test cases, instead test cases must use public methods below.
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/title_onboarding")
	private WebElement leagueTitle;   
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/action_button_text")
	private WebElement continueButton;
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/viewPager")
	private WebElement leagueList;
	
	
    private static final By leagueList2 = By.id("com.fivemobile.thescore:id/viewPager");

    //****ACTION METHODS*****//


	// Public Action Methods allowing test cases to interact with the page elements
    
    /**
     * Gets the text of the league title.
     *
     */
    public String getLeagueTitleText() 
    {
        return leagueTitle.getAttribute("text");
    }
    
    /**
     * Chooses a league by scrolling and clicking on it.
     *
     */
    public void chooseLeague(String league)
    {
    	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.fivemobile.thescore:id/viewPager\")).scrollIntoView(text(\""+ league +"\"));"));	
    	driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.fivemobile.thescore:id/txt_name\" and @text=\"" + league + "\"]")).click();

    }

    //Action Method to scroll through league list based on specified element, using the reusable scroll method in android actions class  
    public void chooseLeague2(String league) {
    	
		scrollToTextWithID(leagueList2, league);
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.fivemobile.thescore:id/txt_name\" and @text=\"" + league + "\"]")).click();
    }
    
    /**
     * Clicks on the continue button to navigate to the team page.
     */
	public void continueToTeamPage()
	{
		continueButton.click();
				
	}	
    
}
