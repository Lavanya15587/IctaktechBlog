package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    private static XSSFWorkbook excelWbook;
    private static XSSFSheet excelWSheet;
    private static XSSFCell cell;


    public static String getTrainerCellData(int RowNum, int ColNum) throws IOException {
        FileInputStream ExcelFile = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/TestData.xlsx");
        excelWbook = new XSSFWorkbook(ExcelFile);
        excelWSheet = excelWbook.getSheetAt(0);
        return excelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
    }


    public static String getCellDataFormat(int RowNum, int ColNum) throws IOException {
        FileInputStream ExcelFile = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/TestData.xlsx");
        excelWbook = new XSSFWorkbook(ExcelFile);
        excelWSheet = excelWbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();
        XSSFCell cell = excelWSheet.getRow(RowNum).getCell(ColNum);
        return formatter.formatCellValue(cell);
    }

    public static String getAdminCellData(int RowNum, int ColNum) throws IOException {
        // Open the Excel file
        FileInputStream ExcelFile = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/TestData.xlsx");
        excelWbook = new XSSFWorkbook(ExcelFile);
        excelWSheet = excelWbook.getSheetAt(1);
        DataFormatter formatter = new DataFormatter();
        cell= excelWSheet.getRow(RowNum).getCell(ColNum);
        return formatter.formatCellValue(cell);
    }
}
