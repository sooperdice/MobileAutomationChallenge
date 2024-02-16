package org.thescore.utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;


//This Class contains reusable android specific methods that can be used across framework

public class AndroidActions extends AppiumUtils{
  
  //dummy driver to use anywhere in class
  AndroidDriver driver;
  public AndroidActions(AndroidDriver driver) 
  {
    
    // Refers to current class variables.
    // Super inherits driver from auper class to initialist, i.e AppiumUtils
    //super(driver);//comment in if inheriting appium driver from appium utils
    this.driver = driver;
  //PageFactory.initElements(new AppiumFieldDecorator(driver),this); //
  }
  

  /**
   * Scrolls to the specified text on the screen.
   */
  public void scrollToText(String text)
  {
  
    driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));

  }
  
  
  /**
   * Scrolls to the specified text within an element identified by the locator.
   */
  public void scrollToTextWithID(By locator, String text)
  {
      String resourceId = driver.findElement(locator).getAttribute("resource-id");
    //driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"" + locator.toString() + "\")).scrollIntoView(text(\""+ text +"\"));")); 
      String locatorString = "new UiScrollable(new UiSelector().resourceId(\"" + resourceId + "\")).scrollIntoView(text(\""+ text +"\"));";
      driver.findElement(AppiumBy.androidUIAutomator(locatorString));

  }
  
  /**
   * Scrolls horizontally to the specified text within an element identified by the locator.
   */
  public void scrollHorizontalToTextWithID(By locator, String text)
  {
      String resourceId = driver.findElement(locator).getAttribute("resource-id");
    //driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"" + locator.toString() + "\")).scrollIntoView(text(\""+ text +"\"));")); 
      String locatorString = "new UiScrollable(new UiSelector().resourceId(\"" + resourceId + "\")).setAsHorizontalList().scrollIntoView(text(\""+ text +"\"));";
      driver.findElement(AppiumBy.androidUIAutomator(locatorString));

  }
  
  /**
   * Performs a swipe action on the specified element in the given direction.
   */
  public void swipeAction(WebElement ele, String direction)
  {
      String script = "mobile: swipeGesture";
      Map<String, Object> args = new HashMap<>();
      args.put("elementId", ((RemoteWebElement) ele).getId());
      args.put("direction", direction.toLowerCase()); // Convert direction to lowercase
      args.put("percent", 0.2);
      ((JavascriptExecutor) driver).executeScript(script, args);
  }


  /**
  /** *Permission handling methods
  **/

  // Reusable Method to handle location permission alert
  public void handleLocationPermissionAlert() {
      handlePermissionAlert("com.fivemobile.thescore:id/btn_allow",
              "com.android.permissioncontroller:id/permission_allow_foreground_only_button");
  }

  // Resuable Method to handle notifications permission alert
  public void handleNotificationsPermissionAlert() {
      handlePermissionAlert("com.android.permissioncontroller:id/permission_allow_button");
  }

  // Private method to handle permission alert based on provided button IDs
  private void handlePermissionAlert(String... buttonIds) {
      for (String buttonId : buttonIds) {
          if (isPermissionAlertPresent(buttonId)) {
              allowPermission(buttonId);
          }
      }
  }

  // Private method to check if the permission alert with the provided button ID is present
  private boolean isPermissionAlertPresent(String permissionButtonId) {
      try {
          return driver.findElement(By.id(permissionButtonId)).isDisplayed();
      } catch (NoSuchElementException e) {
          return false;
      }
  }
  
  // Private method to allow permission by clicking the button with the provided ID
  private void allowPermission(String permissionButtonId) {
      WebElement button = driver.findElement(By.id(permissionButtonId));
      button.click();
      // Add explict wait until the permission alert disappears
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Adjust timeout as needed
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(permissionButtonId)));
  }

}