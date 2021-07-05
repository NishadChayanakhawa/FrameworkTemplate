package home.frameworkTemplate.testHelpers;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.chrome.ChromeOptions;

//import home.downloadManager.helpers.PropertyFileReader;

public class WebDriverHelper {
	private final static String strChromeDriverPath=
			"./src/test/resources/drivers/chromedriver.exe";
	
	private static ChromeDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver",strChromeDriverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		//ChromeOptions options = new ChromeOptions().setHeadless(true); 
		//return new ChromeDriver(options);
		return new ChromeDriver();
	}
	
	public static WebDriver getWebDriver(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			return getChromeDriver();
		}
		else {
			return null;
		}
	}
}
