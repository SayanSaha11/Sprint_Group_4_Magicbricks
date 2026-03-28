package utilities_Tanistha;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private static final String FILE_PATH = System.getProperty("user.dir") 
            + "/src/test/testdata_Tanistha.xlsx";

    //C:\Users\TANISTHA\git\MagicBricks\Day_3_Deliverables\MagicBricks_Sprint_Group4\src\testdata_Tanistha.xlsx

    public static String getCellData(String sheetname,int row, int col) {
        try {
        	FileInputStream fis = new FileInputStream(FILE_PATH);
        	XSSFWorkbook workbook = new XSSFWorkbook(fis);
        	XSSFSheet sheet = workbook.getSheet(sheetname);

        	String data = sheet.getRow(row).getCell(col).toString(); workbook.close(); 
        	return data;

        } catch (Exception e) {
            return "";
        	//System.out.println("Wrong");
        }
    }
}