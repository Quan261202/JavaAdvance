package com.halloween.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcel {

    public void exportListOrder(){
        @SuppressWarnings("resource")
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("listOrder");
        XSSFRow xssfRow = xssfSheet.createRow(3);
        XSSFCell cell = null;
        cell = xssfRow.createCell(0, CellType.STRING);
        cell.setCellValue("Danh sách các loại hàng");
        cell = xssfRow.createCell(0, CellType.STRING);
        cell.setCellValue("STT");
        cell = xssfRow.createCell(1, CellType.STRING);
        cell.setCellValue("Product Name");
        cell = xssfRow.createCell(2, CellType.STRING);
        cell.setCellValue("Category");
        cell = xssfRow.createCell(3, CellType.STRING);
        cell.setCellValue("Price");
        cell = xssfRow.createCell(4, CellType.STRING);
        cell.setCellValue("Amount");
        cell = xssfRow.createCell(5, CellType.STRING);
        cell.setCellValue("To Money");
        FileOutputStream outputStream = null;
        try {
            File file = new File("D://listOrder.xlsx");
            outputStream = new FileOutputStream(file);
            xssfWorkbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
