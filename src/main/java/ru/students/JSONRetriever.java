package ru.students;

import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JSONRetriever {
    public static void main(String[] args) throws IOException, ParseException {
        InputStream inUrl = new URL("https://www.cbr-xml-daily.ru/latest.js").openStream();
        String retrievedJSON = readAllByByte(inUrl);
        saveRetrievedJSON(retrievedJSON);
        JSONParser currencyParser = new JSONParser(retrievedJSON);
        ExcelExport excelExport = new ExcelExport(currencyParser.currencyToRUB, currencyParser.RUBtoCurrency);
        inUrl.close();
        //JSONObject jsonObject = parser.parse(inUrl)
    }

    public static String readAllByByte(InputStream in) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        while (true) {
            int oneByte = in.read();
            if (oneByte != -1) {
                //System.out.print((char) oneByte);
                jsonString.append((char) oneByte);
            } else {
                System.out.println("\nend");
                break;
            }
        }
        System.out.println(jsonString);
        return jsonString.toString();
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
