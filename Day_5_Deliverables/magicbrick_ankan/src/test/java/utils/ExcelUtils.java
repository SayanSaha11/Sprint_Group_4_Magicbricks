package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.util.*;

public class ExcelUtils {

    private static final String path =
        "src/test/resources/testdata/ContactData.xlsx";


    public static String[] getContactDataById(String id) {

        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("Sheet1"); // ✅ FIXED
            DataFormatter formatter = new DataFormatter();

            int rows = sheet.getLastRowNum();

            for (int i = 1; i <= rows; i++) {

                String rowId = formatter.formatCellValue(
                    sheet.getRow(i).getCell(0)
                ).trim();

                System.out.println("Checking ID: " + rowId);

                if (rowId.equals(id.trim())) {

                    String email  = formatter.formatCellValue(sheet.getRow(i).getCell(1));
                    String mobile = formatter.formatCellValue(sheet.getRow(i).getCell(2));
                    String name   = formatter.formatCellValue(sheet.getRow(i).getCell(3));

                    return new String[]{email, mobile, name};
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static List<String> getInvalidCities() {

        List<String> cities = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("Sheet2");

            int rows = sheet.getLastRowNum();

            for (int i = 1; i <= rows; i++) {
                String city = sheet.getRow(i).getCell(0).toString();
                cities.add(city);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cities;
    }
    
    
    

    public static String[] getReviewDataById(String id) {

        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("Sheet3");
            DataFormatter formatter = new DataFormatter();   // ✅ important

            int rows = sheet.getLastRowNum();

            for (int i = 1; i <= rows; i++) {

                String rowId = formatter.formatCellValue(
                    sheet.getRow(i).getCell(0)
                ).trim();

                System.out.println("Checking row ID: " + rowId);

                if (rowId.equals(id.trim())) {

                    String title  = formatter.formatCellValue(sheet.getRow(i).getCell(1));
                    String review = formatter.formatCellValue(sheet.getRow(i).getCell(2));

                    return new String[]{title, review};
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    
    
    public static String getInvalidCityById(String id) {

        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("Sheet2"); // 🔥 CHECK THIS
            DataFormatter formatter = new DataFormatter();

            int rows = sheet.getLastRowNum();

            for (int i = 1; i <= rows; i++) {

                String rowId = formatter.formatCellValue(
                    sheet.getRow(i).getCell(0)
                ).trim();

                System.out.println("Row ID: " + rowId);

                if (rowId.equals(id.trim())) {

                    return formatter.formatCellValue(
                        sheet.getRow(i).getCell(1)
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}