package home.frameworkTemplate.testDataProvider;

import static home.frameworkTemplate.testHelpers.ExcelHelper.getDataAsMaps;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
	@DataProvider(name="getTestDataFromExcel")
	public Object[][] getTestDataFromExcel(Method testMethod) {
		String sheetName=testMethod.getName();
		Object[][] testDataAsMaps=getDataAsMaps(sheetName);
		return testDataAsMaps;
	}
}
