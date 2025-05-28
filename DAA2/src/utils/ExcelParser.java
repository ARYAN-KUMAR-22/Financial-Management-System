package utils;

import model.Transaction;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {
    public static List<Transaction> parse(File file) {
        List<Transaction> transactions = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header
                LocalDate date = row.getCell(0).getLocalDateTimeCellValue().toLocalDate();
                String desc = row.getCell(1).getStringCellValue();
                double amt = row.getCell(2).getNumericCellValue();
                String type = row.getCell(3).getStringCellValue();
                String category = row.getCell(4).getStringCellValue();
                String account = row.getCell(5).getStringCellValue();
                String month = row.getCell(6).getStringCellValue();

                transactions.add(new Transaction(date, desc, amt, type, category, account, month));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}

