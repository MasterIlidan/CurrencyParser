package ru.students;

import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CurrencyCompare {
    public static void main(String[] args) {
        JSONRetriever jsonRetriever = new JSONRetriever();
        String retrievedJSON;
        try {
            retrievedJSON = jsonRetriever.retrieveJSON();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка во время загрузки курсов",e);
        }
        JSONParser currencyParser;
        try {
            currencyParser = new JSONParser(retrievedJSON);
        } catch (ParseException e) {
            throw new RuntimeException("Произошла ошибка во время парсинга данных",e);
        }
        ExcelExport excelExport = new ExcelExport(currencyParser.currencyToRUB, currencyParser.RUBtoCurrency);
        excelExport.exportToExcel();
        CurrencyComparator.compareCurrencies(currencyParser);
        saveRetrievedJSON(retrievedJSON);
    }
    public static void saveRetrievedJSON(String string) {
        String filepath = "./latest.json";
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        try (FileOutputStream outputStream = new FileOutputStream(filepath)) {
            for (byte oneByte : bytes) {
                outputStream.write(oneByte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
