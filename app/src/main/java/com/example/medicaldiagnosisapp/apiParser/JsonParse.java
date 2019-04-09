package com.example.medicaldiagnosisapp.apiParser;

import java.io.IOException;
import java.io.InputStream;

/**
 * JsonParse parses a kml file, creating a String for easy usage in java
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class JsonParse {

    /**
     * Creates a String from an InputStream
     * @param inputStream the location of the JSON resource file
     * @return String if not null
     */
    public static String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

}
