package ru.students;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CurrencyComparator {

    static private JSONParser getLatestJSON() throws ParseException {
        File file = new File("./latest.json");
        String latestJSO = null;
        try (InputStream inputStream = new FileInputStream(file)) {
            latestJSO = JSONRetriever.readAllByByte(inputStream);
        } catch (IOException e) {
            System.out.println("Предыдущие курсы не найдены. Либо программа запущена впервые, либо файл был удален");
        }
        if (latestJSO == null) {
            return null;
        }
        return new JSONParser(latestJSO);
    }

    static void compareCurrencies(JSONParser compareData) {
        JSONParser latestJSON;
        try {
            latestJSON = getLatestJSON();
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing latest JSON", e);
        }
        if (latestJSON == null) {
            return;
        }
        for (String key : compareData.currencyToRUB.keySet()) {
            double latestValue = latestJSON.currencyToRUB.get(key);
            double newValue = compareData.currencyToRUB.get(key);
            if (newValue > latestValue) {
                System.out.printf("%s стал вышел, было: %2f, стало %2f\n", key, latestValue, newValue);
            } else if (newValue < latestValue) {
                System.out.printf("%s стал ниже, было: %2f, стало %2f\n", key, latestValue, newValue);
            } else if (newValue == latestValue) {
                System.out.printf("%s не изменился: %2f\n", key, latestValue);
            }
        }
    }
}
