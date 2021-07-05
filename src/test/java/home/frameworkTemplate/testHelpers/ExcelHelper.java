package home.frameworkTemplate.testHelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	private static Workbook workbook;
	
	public static void openWorkbook(String workbookPath) {
		File workbookFile=new File(workbookPath);
		try {
			FileInputStream workbookFileInputStream=
					new FileInputStream(workbookFile);
			ExcelHelper.workbook=
					new XSSFWorkbook(workbookFileInputStream);
			workbookFileInputStream.close();
			
		} catch (IOException e) {
			ExcelHelper.workbook=null;
		}
	}
	
	public static void closeWorkbook() {
		try {
			ExcelHelper.workbook.close();
		} catch (IOException e) {
			// Do Nothing
		}
	}
	
	public static Object[][] getDataAsMaps(String sheetName) {
		List<Map<String,String>> testDataAsMapsList=
				new ArrayList<Map<String,String>>();
		Sheet testDataSheet=ExcelHelper.workbook.getSheet(sheetName);
		for(Row testDataRow:testDataSheet) {
			if(testDataRow.getRowNum()==0) continue;
			Map<String,String> testDataAsMap=new HashMap<String,String>();
			for(Cell testDataCell:testDataRow) {
				String parameterName=
						testDataSheet.getRow(0)
						.getCell(testDataCell.getColumnIndex()).getStringCellValue();
				String parameterValue=null;
				if(testDataCell==null || testDataCell.getCellType()==CellType.BLANK) {
					parameterValue="";
				}
				else {
					parameterValue=testDataCell.getStringCellValue();
				}
				testDataAsMap.put(
					parameterName,
					parameterValue);
			}
			testDataAsMapsList.add(testDataAsMap);
		}
		
		Object[][] testDataAsMaps=new Object[testDataAsMapsList.size()][1];
		for(int rowCount=0;rowCount<testDataAsMapsList.size();rowCount++) {
			testDataAsMaps[rowCount][0]=testDataAsMapsList.get(rowCount);
		}
		
		return testDataAsMaps;
	}
	

}
