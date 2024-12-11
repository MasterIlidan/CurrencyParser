package ru.students;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class JSONRetriever {
    public static void main(String[] args) throws IOException, ParseException {
        InputStream inUrl = new URL("https://www.cbr-xml-daily.ru/latest.js").openStream();

        JSONParser currencyParser = new JSONParser(readAllByByte(inUrl));
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
}
