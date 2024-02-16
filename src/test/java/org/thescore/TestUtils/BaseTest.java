package org.thescore.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.thescore.pageObjects.android.WelcomePage;
import org.thescore.utils.AndroidActions;
import org.thescore.utils.AppiumUtils;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends AppiumUtils {



	public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public WelcomePage welcomePage;
    public AndroidActions androidActions;
    public UiAutomator2Options options;

	@BeforeClass
	public void ConfigureAppium() throws IOException
	{
		//Capture Data from properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\data.properties"); //use system.getProperty to identify project folder and then use path of any files
		prop.load(fis);
		String ipAddress = prop.getProperty("appiumIpAddress"); // Get ip and assign to variable
		String port = prop.getProperty("appiumPort");
		String appiumJsFilePath = prop.getProperty("appiumJsFilePath");
        System.out.println("Appium JS File Path: " + appiumJsFilePath); // Log the file path

		
    	//Start Appium Programmatically  with ip, port, and jsfilepath from properties file
		service = startAppiumServer(ipAddress, Integer.parseInt(port), appiumJsFilePath); //parseInt converts string to integer
		
		//Initiate UIAutomator2 with capabilities, comment/uncomment required ones
		options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("androidDeviceName"));
		options.setApp(System.getProperty("user.dir")+("\\src\\test\\resources\\theScore.apk"));
        //options.setCapability("autoAcceptAlerts", true);
        //options.setCapability("autoDismissAlerts", true);
        options.setCapability("autoGrantPermissions", true);     
        //options.setAppActivity("com.fivemobile.thescore.ui.MainActivity");
        //options.setCapability("fullReset", true);		
        
		// Initialize Appium driver 
        driver = new AndroidDriver(service.getUrl(), options);	//get URL from appiumserver        
        
        // Implicit wait for elements to appear within n seconds otherwise timeout	
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); 	
		
		// Initiate Welcome Page here since we know it's always first page of app in tests
		welcomePage=new WelcomePage(driver); 
		
	}
	
    // Activate the tested Android application
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException, InterruptedException {
		driver.activateApp("com.fivemobile.thescore");
		System.out.println("Starting App");
	}
	
	
	/**
    // Clear the app data and cache after each test method
	 * This method ensures a clean test environment before each test method.
	 */
	@AfterMethod(alwaysRun = true)
	public void clearAppData() throws IOException, InterruptedException {
		;
		if (driver != null) {
			String appPackage = driver.getCapabilities().getCapability("appPackage").toString();
			driver.executeScript("mobile: clearApp", ImmutableMap.of("appId", appPackage));
			System.out.println("App data and cache cleared successfully.");
		} else {
			System.out.println("Driver is not initialized.");
		}
	}

    // Terminate the tested Android application and quit the driver
	@AfterClass(alwaysRun = true)
	public void tearDown() throws InterruptedException, IOException {

		driver.terminateApp("com.fivemobile.thescore");
		System.out.println("Closing App");

		if (driver != null) {
			System.out.println("Closing WebDriver session...");
			driver.quit();
		}
	}

    // Stop the Appium server after the entire test suite finishes
	@AfterSuite(alwaysRun = true)
	public void tearDownSuite() {
		if (service != null && service.isRunning()) {
			System.out.println("Stopping Appium service...");
			service.stop();
		}

	}
	

}