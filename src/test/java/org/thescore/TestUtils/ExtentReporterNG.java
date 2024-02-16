package org.thescore.TestUtils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports getReporterObject() //change to static, so that methods can be accessed directly via class level without creating object
	{
		//ExtentReports , ExtentSparkReporter
		String path = System.getProperty("user.dir")+"//reports//index.html"; // creates new folder for report in user project dir
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("The Score Mobile Automation Challenge Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ashwin Gavai");
		
		return extent;		
	}
		
}
