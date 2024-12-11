package ru.students;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

public class JSONParser {


    private org.json.simple.parser.JSONParser parser;
    HashMap<String,Double> RUBtoCurrency = new HashMap<>();
    HashMap<String,Double> currencyToRUB = new HashMap<>();
    private JSONObject root;
    private JSONObject rates;
    JSONParser(String json) throws ParseException {
        parser = new org.json.simple.parser.JSONParser();
        root = (JSONObject) parser.parse(json);
        rates = (JSONObject) root.get("rates");
        getAllRates();
    }
    private void getAllRates() {
        for (Object key : rates.keySet()) {
            Double rate = (Double) rates.get(key);
            RUBtoCurrency.put(key.toString(), rate);
            currencyToRUB.put(key.toString(), 1/rate);
        }

    }
}
