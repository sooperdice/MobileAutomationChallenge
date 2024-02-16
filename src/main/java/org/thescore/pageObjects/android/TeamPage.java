package org.thescore.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This POM Class contains the locators and action methods for Team page 

public class TeamPage {
	
	AndroidDriver driver;
	
	//constructor to use the android driver from test case. one time set up. 
	public TeamPage(AndroidDriver driver)
	{

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);   //contructs selenium driver.findElements behind the scenes to use in test cases
	}
	
	//***ELEMENTS AND LOCATORS **** //	
	
	@AndroidFindBy(id="com.fivemobile.thescore:id/team_name")
	private WebElement teamName;    // elements are marked as private so as to not use them in test cases, instead test cases must use public methods below.
	
	//Sub TABS
	
	//Player Stats Tab
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"PLAYER STATS\"]")
	private WebElement playerStatsTab;
	
	@AndroidFindBy(xpath="//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.fivemobile.thescore:id/recyclerView\"]/android.view.ViewGroup[1]")
	private WebElement playerStatsHeader;
		
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"TEAM STATS\"]")
	private WebElement teamStatsTab;
	

	//ACTION METHODS
	
	// Method to get the text of the team name
    public String getTeamNameText() 
    {
        return teamName.getAttribute("text");
    }    
    
	// Method to select the Player Stats tab
    public void selectPlayerSubTab()
    {
    	playerStatsTab.click();
    }
    
	// Method to get the WebElement of the Player Stats tab
    public WebElement getPlayerStatsTab() 
    {
        return playerStatsTab;
    }
    
	// Method to check if a tab is selected
    public boolean isTabSelected(WebElement tab) 
    {
        return tab.isSelected();
    }

    
    public boolean verifyplayerStatsHeaders()
    {
    	// Check if the player stats header element is displayed
        if (playerStatsHeader.isDisplayed()) {
            // Find the child element (player stats header with expected text)
            WebElement childElement = playerStatsHeader.findElement(By.xpath("//android.widget.TextView[@resource-id='com.fivemobile.thescore:id/txt_name' and @text='PLAYERS']"));
            
            // Check if the child element is displayed
            if (childElement.isDisplayed()) {
                System.out.println("Player stats header with expected text is displayed.");
                return true;
            } else {
                System.out.println("Player stats header is displayed, but the header with expected text is not found.");
                return false;
            }
        } else {
            System.out.println("Player stats header is not displayed.");
            return false;
        }    
     }
    
	// Method to verify player stats headers
    public boolean verifyPlayerStatsHeaders() {
    	try {
            WebElement childElement = playerStatsHeader.findElement(By.xpath("//android.widget.TextView[@resource-id='com.fivemobile.thescore:id/txt_name' and @text='PLAYERS']"));
            return childElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }   

        
}
