package ru.students;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ExcelExport {

    XSSFWorkbook workbook = new XSSFWorkbook();
    HashMap<String, Double> RUBToCurrency;
    HashMap<String, Double> currencyToRUB;

    ExcelExport(HashMap<String, Double> currencyToRUB,
                HashMap<String, Double> RUBToCurrency) {
        this.RUBToCurrency = RUBToCurrency;
        this.currencyToRUB = currencyToRUB;
    }

    public void exportToExcel() {
        //Создаем новый лист в книге
        XSSFSheet sheet = workbook.createSheet("Курсы валют к рублю");
        int counter = 0;
        //Записываем даннные в ячейки
        for (String currency : currencyToRUB.keySet()) {
            Row dataRow = sheet.createRow(counter++);
            dataRow.createCell(0).setCellValue(currency);
            dataRow.createCell(1).setCellValue(currencyToRUB.get(currency));
        }
        //Создаем новый лист в книге
        XSSFSheet sheet2 = workbook.createSheet("Курсы рубля к валютам");
        counter = 0;
        //Записываем даннные в ячейки
        for (String currency : RUBToCurrency.keySet()) {
            Row dataRow = sheet2.createRow(counter++);
            dataRow.createCell(0).setCellValue(currency);
            dataRow.createCell(1).setCellValue(RUBToCurrency.get(currency));
        }

        //Записываем книгу Excel в файл
        String filepath = "./currencies.xlsx";
        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println("Ошибка экспорта в Excel");
            e.printStackTrace();
        }
    }
}
