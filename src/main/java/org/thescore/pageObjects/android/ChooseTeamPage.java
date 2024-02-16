package org.thescore.pageObjects.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.thescore.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This POM Class contains the locators and action methods for Choose Team page 


public class ChooseTeamPage extends AndroidActions {
	
	AndroidDriver driver;
	
	//constructor to use the android driver from test case. one time set up. 
	public ChooseTeamPage(AndroidDriver driver)
	{
		//super calls parent class constructor
		super(driver);
		// this Refers to current class variables.
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);   //contructs selenium driver.findElements behind the scenes to use in test cases
	}
	
	//***ELEMENTS AND LOCATORS **** //
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/title_onboarding")
	private WebElement teamTitle;    // elements are marked as private so as to not use them in test cases, instead test cases must use public methods below.
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/action_button_text")
	private WebElement continueButton;
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/search_bar_placeholder")
	private WebElement searchBar;
	
	@AndroidFindBy(className="android.widget.Switch")
	private WebElement errorToggle;
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/refresh")
	private WebElement refreshButton;
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/error_view")
    private WebElement errorDisplay;



	
	//These locators are defined as a static final By object, to be reused the locator across different methods or classes.
    private static final By teamList = By.id("com.fivemobile.thescore:id/viewPager");
    private static final By leagueTab = By.id("com.fivemobile.thescore:id/tabLayout");


    
    //****ACTION METHODS*****//
    
    //This method returns the text attribute for team Title 
    public String getTeamTitleText() 
    {
        return teamTitle.getAttribute("text");
    }
    
	public void chooseLeagueandTeam(String league, String team) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.fivemobile.thescore:id/tabLayout\"))"
						+ ".setAsHorizontalList().scrollIntoView(new UiSelector().text(\"" + league + "\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + league + "\"]")).click();
		driver.findElement(AppiumBy.androidUIAutomator(
 			"new UiScrollable(new UiSelector().resourceId(\"com.fivemobile.thescore:id/viewPager\")).scrollIntoView(text(\""
						+ team + "\"));"));
		driver.findElement(
				By.xpath("//android.widget.TextView[@resource-id=\"com.fivemobile.thescore:id/txt_name\" and @text=\""
						+ team + "\"]"))
				.click();
	}
    
	
    //This method will scroll through desired league and select desired team

    public void chooseLeagueandTeam2(String league, String team)
    {   	
    	scrollHorizontalToTextWithID(leagueTab, league);
    	driver.findElement(By.xpath("//android.widget.TextView[@text=\""+ league +"\"]")).click();
    	scrollToTextWithID(teamList, team);
    	driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.fivemobile.thescore:id/txt_name\" and @text=\""+team+"\"]")).click(); 	  
    }
    
    //This method will click the continue button
	public void continueButton()
	{
		continueButton.click();
	}	
	
	public boolean searchAndhandleError()
	{
		searchBar.click();
		errorToggle.click();
		refreshButton.click();
        // Wait for the error display element to become invisible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOf(errorDisplay));
        // Check if the error display element is displayed
        boolean isErrorDisplayed = errorDisplay.isDisplayed();
        return isErrorDisplayed;
		
	}
    
}
