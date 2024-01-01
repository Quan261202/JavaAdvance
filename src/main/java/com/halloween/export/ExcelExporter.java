package com.halloween.export;

import com.halloween.model.CategoryModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
public class ExcelExporter {
    public static <T> void exportTo(List<T> sources, String fileName) {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + fileName;
        try (FileOutputStream outputStream = new FileOutputStream(fileLocation); XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Products");
            Row header = sheet.createRow(0);
            XSSFFont font = workbook.createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);

            Field[] fields = sources.get(0).getClass().getDeclaredFields();

            for(int j = 0; j < fields.length; ++j) {
                Cell headerCell = header.createCell(j);
                headerCell.setCellValue(fields[j].getName());
            }

            for (int i = 0; i < sources.size(); ++i) {
                Row row = sheet.createRow(i + 1);
                for(int j = 0; j < fields.length; ++j) {
                    fields[j].setAccessible(true);
                    Object value = fields[j].get(sources.get(i));
                    Cell cell = row.createCell(j);
                    cell.setCellValue(Objects.isNull(value) ? "" : value.toString());
                }
            }
            workbook.write(outputStream);
        } catch (IOException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        exportTo(List.of(new CategoryModel(1, "1", "1")), "a.xlsx");
    }
}
