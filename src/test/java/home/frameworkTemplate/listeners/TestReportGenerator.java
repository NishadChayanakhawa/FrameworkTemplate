package home.frameworkTemplate.listeners;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReportGenerator implements ITestListener{
	ExtentReports report;
	ExtentTest test;
	final static String reportPath="./TestResults/";
	
	
	public void onTestSuccess(ITestResult result) {
		//System.out.println("  - " + result.getTestContext().getAttribute("testData"));
		try {
			test.log(Status.INFO, result.getTestContext().getAttribute("testData").toString());
		}
		catch(Exception e) {
			test.log(Status.INFO, "Test data not available");
		}
		test.log(Status.PASS, "Passed");
		System.out.println("    .........Passed");
	}

	
	public void onTestFailure(ITestResult result) {
		try {
			test.log(Status.INFO, result.getTestContext().getAttribute("testData").toString());
		}
		catch(Exception e) {
			test.log(Status.INFO, "Test data not available");
		}
		test.log(Status.FAIL, result.getThrowable().getMessage());
		System.out.println("    .........Failed");
	}

	
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		System.out.print("  - " + result.getMethod().getMethodName());
	}

	
	public void onStart(ITestContext context) {
		System.out.println("* Executing " + context.getName());
		report=new ExtentReports();
		ExtentSparkReporter reporter=new ExtentSparkReporter
				(new File(reportPath + context.getName() + ".html"));
		report.attachReporter(reporter);
	}

	
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
