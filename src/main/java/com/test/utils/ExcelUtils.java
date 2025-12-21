package com.test.utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] getTestData(String sheetName) {

        Object[][] data = null;

        try {
            FileInputStream fis = new FileInputStream("testdata/LoginData.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : cell.toString();
                }
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel", e);
        }

        return data;
    }
}
