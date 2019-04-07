package com.example.medicaldiagnosisapp.apiParser;

import java.io.IOException;
import java.io.InputStream;

public class JsonParse {

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
