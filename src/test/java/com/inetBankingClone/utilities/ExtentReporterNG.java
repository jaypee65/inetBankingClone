package com.inetBankingClone.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG extends TestListenerAdapter {

	public ExtentSparkReporter reporter;
	public static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		String path = System.getProperty("user.dir")+"/reports/"+repName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Jeyapriya");
		return extent;
				
	}
}
