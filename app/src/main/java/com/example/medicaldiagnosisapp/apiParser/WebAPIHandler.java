package com.example.medicaldiagnosisapp.apiParser;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * WebAPIHandler starts the reading of an online API in the background
 * the moment the application begins. Makes resource extracting easier
 * when required by the application.
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class WebAPIHandler extends AsyncTask<String, Void, JSONObject>{

    private static final String TAG = "WebAPIHandler";

    /**
     * Tries to connect to the web api and returns an error if an exception if
     * faced
     * Part of AsynTask implementation
     * @param reqUrl the url of the web api source in String
     * @return response JSONObject containing the result of the query on the web api
     */
    @Override
    protected JSONObject doInBackground(String... reqUrl) {
        JSONObject response = null;
        try {
            URL url = new URL(reqUrl[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = new JSONObject(convertStreamToString(in));
        } catch (final JSONException e) {
            Log.e(TAG, "JSONException: " + e.getMessage());
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    /**
     * Part of AsynTask implementation
     * @param result returns the JSONObject containing the api query
     */
    @Override
    protected void onPostExecute(JSONObject result){
        super.onPostExecute(result);
    }

    /**
     * Converts an InputStream to String using StringBuilder
     * @param is the InputStream containing the result of api query
     * @return sb the String of the result of the api query
     */
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
