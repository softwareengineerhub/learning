package com.app.ch11.xls.processor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataProcessorImpl implements DataProcessor {

    @Override
    public void writeToFile(String filePath, List<BusinessData> data) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("BusinessData");
        //header
        int indexRow = 0;
        Row row = sheet.createRow(indexRow++);
        int indexCell = 0;
        Cell cell = row.createCell(indexCell++);
        cell.setCellValue("Name");
        cell = row.createCell(indexCell++);
        cell.setCellValue("Age");
        //data
        for (BusinessData bd : data) {
            row = sheet.createRow(indexRow++);
            indexCell = 0;
            cell = row.createCell(indexCell++);
            cell.setCellValue(bd.getName());
            cell = row.createCell(indexCell++);
            cell.setCellValue(bd.getAge());
        }
        try (OutputStream out = new FileOutputStream(new File(filePath))) {
            workbook.write(out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<BusinessData> readFromFile(String filePath) {
        try {
            List<BusinessData> list = new ArrayList<>();

            Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath));

            Sheet sheet = workbook.getSheetAt(0);
            int indexRow = 0;
            for (Row row : sheet) {
                if (indexRow == 0) {
                    indexRow++;
                    continue;
                }
                BusinessData bd = new BusinessData();
                Cell cell = row.getCell(0);
                String nameValue = cell.getStringCellValue();
                bd.setName(nameValue);
                cell = row.getCell(1);
                double ageValue = cell.getNumericCellValue();
                bd.setAge((int)ageValue);
                list.add(bd);
            }
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
