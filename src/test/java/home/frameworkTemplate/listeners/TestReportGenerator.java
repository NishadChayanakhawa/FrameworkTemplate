package home.frameworkTemplate.listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


@SuppressWarnings("unchecked")
public class TestReportGenerator implements ITestListener{
	String testName;
	ExtentReports report;
	ExtentTest test;
	final static String reportPath="./TestResults/";

	private void createTest(ITestResult result) {
		testName=(String)result.getTestContext().getAttribute("testName");
		if (testName == null) {
			testName="Test Name Not Found. Method Name: " +
					result.getMethod().getMethodName(); 		
		}
		else {
			result.getTestContext().removeAttribute("testName");
		}
		test=report.createTest(testName);
	}

	private void printTestSteps(ITestResult result) {
		List<String> testSteps=
				(List<String>)result.getTestContext().getAttribute("testSteps");
		if (testSteps == null) {
			test.log(Status.INFO, "Test steps not available.");
		}
		else {
			for (String testStep : testSteps) {
				test.log(Status.INFO, testStep);
			}
			result.getTestContext().removeAttribute("testSteps");
		}
	}

	//	private void cleanupContextAttributes(ITestResult result) {
	//		for(String attributeName : result.getTestContext().getAttributeNames()) {
	//			result.getTestContext().removeAttribute(attributeName);
	//		}
	//	}

	private void takeScreenshot(ITestResult result,String filePath) {
		WebDriver driver=(WebDriver)result.getTestContext().getAttribute("driver");
		if (driver==null) {
			System.out.println("Driver is not attached to test context");
		}
		else {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(filePath);
			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				System.out.println("Driver is attached but saving screenshot failed.");
			}
			result.getTestContext().removeAttribute("driver");
		}
	}


	public void onTestSuccess(ITestResult result) {
		createTest(result);
		printTestSteps(result);
		test.log(Status.PASS, "Passed");
		System.out.println("    .........Passed");
	}


	public void onTestFailure(ITestResult result) {
		createTest(result);
		printTestSteps(result);
		takeScreenshot(result,"./TestResults/Screenshots/" + testName.replace(" ","_") + ".png");
		test.log(Status.FAIL,
				result.getThrowable().getMessage()
				);
		test.addScreenCaptureFromPath("./Screenshots/" + testName.replace(" ","_") + ".png");
		System.out.println("    .........Failed");
	}


	public void onTestStart(ITestResult result) {
		System.out.print("  - " + result.getMethod().getMethodName());
	}


	public void onStart(ITestContext context) {
		System.out.println("* Executing " + context.getName());
		report=new ExtentReports();
		ExtentSparkReporter reporter=new ExtentSparkReporter
				(new File(reportPath + context.getName() + ".html"));
		reporter.config().setOfflineMode(true);
		report.attachReporter(reporter);
	}


	public void onFinish(ITestContext context) {
		report.flush();
	}
}
