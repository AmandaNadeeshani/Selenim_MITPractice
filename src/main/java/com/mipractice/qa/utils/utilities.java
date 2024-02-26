package com.mipractice.qa.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import static java.sql.Types.NUMERIC;
import static javax.management.openmbean.SimpleType.STRING;

public class utilities {

    public static final int Imlicit_WaitTime=10000 ;
    public static final int Page_waitTime=15000;

    public static String generateEmailwithtimeStamp(){
        Date date = new Date();
        String timestamp = date.toString().replace(" ", "_").replace(":", "_");
        return "a" + timestamp + "@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName) throws IOException, InvalidFormatException {
        File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mipractice\\qa\\testdata\\TestDataMIT.xlsx");
        FileInputStream fileExcel = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileExcel);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i + 1);
            for (int j = 0; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = Integer.toString((int) cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();
                        break;
                }
            }
        }
        return data;
    }

}
