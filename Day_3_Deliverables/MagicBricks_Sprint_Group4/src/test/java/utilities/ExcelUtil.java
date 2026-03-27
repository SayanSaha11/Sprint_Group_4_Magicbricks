package utilities;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtil {

    public static String getCellData(String sheetname,int row, int col) {
        try {
        	FileInputStream fis = new FileInputStream("C:\\Users\\TANISTHA\\Desktop\\testdata_Tanistha.xlsx");
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