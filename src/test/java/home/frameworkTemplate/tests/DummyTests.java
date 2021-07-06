package home.frameworkTemplate.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static home.frameworkTemplate.testHelpers.WebDriverHelper.getWebDriver;
@SuppressWarnings("unchecked")
public class DummyTests {
	WebDriver driver;
	
	@BeforeTest
	public void setupTest() {
		driver=getWebDriver("chrome");
	}
	
	@AfterTest
	public void tearDownTest() {
		driver.close();
	}
	
	@Test
	public void dummyTest1(ITestContext testContext) {
		driver.get("https://www.google.co.in/");
		testContext.setAttribute("testSteps", new ArrayList<String>());
		testContext.setAttribute("testName","Dummy Test 1");
		testContext.setAttribute("driver",driver);

		((ArrayList<String>)testContext.getAttribute("testSteps")).add(
				"This is my Step1");
		Assert.assertTrue(true,"Dummy Test1 Assertion failed");
		((ArrayList<String>)testContext.getAttribute("testSteps")).add(
				"Step1 has passed");
	}

	@Test
	public void dummyTest2(ITestContext testContext) {
		driver.get("https://www.google.co.in/");
		testContext.setAttribute("testSteps", new ArrayList<String>());
		testContext.setAttribute("testName","Dummy Test 2");
		testContext.setAttribute("driver",driver);
		
		((ArrayList<String>)testContext.getAttribute("testSteps")).add(
				"This is my Step1");
		Assert.assertTrue(false,"Dummy Test2 Assertion failed");
		((ArrayList<String>)testContext.getAttribute("testSteps")).add(
				"Step1 has passed");
	}
	
	@Test
	public void dummyTest3() {
		driver.get("https://www.google.co.in/");
		Assert.assertTrue(false,"Dummy Test3 Assertion failed");
	}
}
