package org.thescore.utils;

import java.io.File;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

//This Class contains reusable Utlities that can be used across framework
public class AppiumUtils {
	
	public AppiumDriverLocalService service;

	/**
     * Starts the Appium server with the IP address and port.
     *
     */
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port, String appiumJsFilePath)
	{
        System.out.println("Initializing Appium Service");


    	service = new AppiumServiceBuilder().withAppiumJS(new File(appiumJsFilePath))
    			.withIPAddress(ipAddress).usingPort(port).build();
		service.start();
		return service;
	}
	
	//This utility  is used to read data from json and convert it to hashmap e.g for data driven testing	
	public List <HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException 
	{		
		
		//CONVERT JSON file content to string 
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		
		//Convert json string to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
	}
	
	
	//This utility takes the test case name and the AppiumDriver instance as parameters and captures a screenshot using the driver
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException
	
	{
		File source = driver.getScreenshotAs(OutputType.FILE); // capture screenshot in raw format
		String destinationFile = System.getProperty("user.dir")+"//reports//"+testCaseName+".png"; //copy screenshot into destination path using test case name
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile; //return file for extent report to use
		
	}
	

}
