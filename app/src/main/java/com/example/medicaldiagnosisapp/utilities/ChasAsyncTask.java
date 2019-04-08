package com.example.medicaldiagnosisapp.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.medicaldiagnosisapp.apiParser.JsonParse;
import com.example.medicaldiagnosisapp.entities.Chas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * ChasAsyncTask starts the reading of resources in the background
 * the moment the application begins. Makes resource extracting easier
 * when required by the application.
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class ChasAsyncTask extends AsyncTask<Void, Void, Chas[]> {

    private static final String TAG = ChasAsyncTask.class.getSimpleName();

    private ChasTaskCallback callback;

    private Context context;

    /**
     * In order to get context specific information. e.g. getting assets
     * @param myContext the current instance of the activity
     */
    public ChasAsyncTask (ChasTaskCallback callback, Context myContext) {
        this.context = myContext;
        this.callback = callback;
    }

    /**
     * Part of AsynTask implementation
     * Checks if this activity has been instantiated before
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onPreExecuteChasTask();
        }
    }

    /**
     * Part of AsynTask implementation
     * Parses the resource file while other activities are running
     * @param voids
     * @return null if successful, error message if exception occurred
     */
    @Override
    protected Chas[] doInBackground(Void... voids) {
        try {
            //URL url = new URL(CHAS_LOCATION_URL);
            //String result = NetworkUtils.getResponseFromHttpUrl(url);
            String result = JsonParse.inputStreamToString(context.getAssets().open("chas.geojson"));
            Log.v("tag", "read json file from assets" );
            return parseJSON(result);
        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Part of AsynTask implementation
     * Executed after AsynTask finishes
     * @param chass Object array that contain all the markers
     */
    @Override
    protected void onPostExecute(Chas[] chass) {
        if (callback != null && chass != null) {
            callback.onPostExecuteChasTask(chass);
        }
    }

    /**
     * Parses a JSON file to get relevant information
     * @param results String of the JSON
     * @return chass Object array that contain all the markers
     */
    private Chas[] parseJSON(String results){

        JSONArray features = null;
        Chas[] chass = null;

        try {
            JSONObject chasObject = new JSONObject(results);
            features = chasObject.getJSONArray("features");
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        if (features != null) {
            chass = new Chas[features.length()];
            int counter = 0;
            for (int i = 0; i < features.length(); i++) {
                try {
                    JSONObject feature = features.getJSONObject(i);

                    JSONObject geometry = feature.getJSONObject("geometry");
                    if (geometry.has("coordinates")) {
                        JSONArray coordinates = geometry.getJSONArray("coordinates");
                        double lat = coordinates.getDouble(1);
                        double lng = coordinates.getDouble(0);
                        JSONObject properties = feature.getJSONObject("properties");
                        String name = properties.getString("HCI_NAME");
                        String street = properties.getString("STREET_NAME");
                        String building = "";
                        if(properties.has("BUILDING_NAME")) {
                            building = "\n" + properties.getString("BUILDING_NAME");
                        }
                        String address = street + building;
                        String postalCode = "\nS" + properties.getString("POSTAL_CD");

                        chass[counter] = new Chas(lat, lng, name, address, postalCode);
                        counter++;
                    }

                } catch (JSONException e) {
                    Log.e(TAG,String.valueOf(e));
                }

            }
        }

        return chass;
    }

    /**
     * Interface of TaskCallBack
     * Implementation of onPreExecute_Task and
     * Implementation of onPostExecute_Task has been
     * implemented here
     */
    public interface ChasTaskCallback {
        void onPreExecuteChasTask();

        void onPostExecuteChasTask(Chas[] chass);
    }

}
