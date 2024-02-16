package org.thescore.TestUtils;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.thescore.utils.AppiumUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	AppiumDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		// This method runs before running any tests
		// test= extent.createTest(result.getMethod().getMethodName()); // ITestult  keeps all test information in result variable, this gathers the method namend stores it in test object. i..

		String methodName = result.getMethod().getMethodName();
		Object[] parameters = result.getParameters();
		String parameterString = Arrays.toString(parameters);
		test = extent.createTest(methodName + " with parameters: " + parameterString);
		test.log(Status.INFO, "Test Started");
	}
	

	@Override
	public void onTestSuccess(ITestResult result) {
        // This listener method runs upon successful test run
        String methodName = result.getMethod().getMethodName();
        test.log(Status.PASS, methodName + " passed successfully");
    
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
	    // This method runs upon failed test run
		test.fail(result.getThrowable()); // gets error message from logs if test failed
		
		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); // GetTesClass from testng xml file, getRealClass is the real class inside test script. get field retrieves the driver used by test
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		e1.printStackTrace();
		} 
				
		try {
		test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName()); // add screenshot to report using screenshot location, and test case name
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	 // TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// to end reporting ready for viewing
		extent.flush();
	}

}
