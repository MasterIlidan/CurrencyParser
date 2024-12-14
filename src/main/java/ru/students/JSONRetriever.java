package ru.students;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class JSONRetriever {
    public String retrieveJSON() throws IOException {
        InputStream inUrl = new URL("https://www.cbr-xml-daily.ru/latest.js").openStream();
        String retrievedJSON = readAllByByte(inUrl);

        inUrl.close();

        //JSONObject jsonObject = parser.parse(inUrl)
        return retrievedJSON;
    }

    public static String readAllByByte(InputStream in) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        while (true) {
            int oneByte = in.read();
            if (oneByte != -1) {
                //System.out.print((char) oneByte);
                jsonString.append((char) oneByte);
            } else {
                //System.out.println("\nend");
                break;
            }
        }
        //System.out.println(jsonString);
        return jsonString.toString();
    }


}
