package ru.students;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class CurrencyComparator {
    JSONObject latestJSON;
    void getLatestJSON() throws ParseException {
        File file = new File("./latest.json");
        String latestJSO = null;
        try (InputStream inputStream = new FileInputStream(file)) {
            latestJSO = JSONRetriever.readAllByByte(inputStream);
        } catch (IOException e) {
            System.out.println("Latest JSON not found");
            e.printStackTrace();
        }
        if (latestJSO == null) {
            return;
        }
        latestJSON = (JSONObject) new JSONParser().parse(latestJSO);
    }

    static void compareCurrencies() {

    }
}
