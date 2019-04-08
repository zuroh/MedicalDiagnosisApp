package com.example.medicaldiagnosisapp.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.medicaldiagnosisapp.apiParser.JsonParse;
import com.example.medicaldiagnosisapp.entities.Aed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * AedAsyncTask starts the reading of resources in the background
 * the moment the application begins. Makes resource extracting easier
 * when required by the application.
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class AedAsyncTask extends AsyncTask<Void, Void, Aed[]> {

    private static final String TAG = AedAsyncTask.class.getSimpleName();

    //private final String AED_LOCATION_URL = "https://data.gov.sg/dataset/4b69ea5a-72a7-4c41-b065-e30021a150a6/download";

    private AedTaskCallback callback;

    private Context context;

    /**
     * In order to get context specific information. e.g. getting assets
     * @param myContext the current instance of the activity
     */
    public AedAsyncTask (AedTaskCallback callback, Context myContext) {
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
            callback.onPreExecuteAedTask();
        }
    }

    /**
     * Part of AsynTask implementation
     * Parses the resource file while other activities are running
     * @param voids
     * @return null if successful, error message if exception occurred
     */
    @Override
    protected Aed[] doInBackground(Void... voids) {
        try {
            //URL url = new URL(AED_LOCATION_URL);
            //String result = NetworkUtils.getResponseFromHttpUrl(url);
            String result = JsonParse.inputStreamToString(context.getAssets().open("aedVerified.geojson"));
            Log.v("tag", "read json file from assets" );
            return parseJSON(result);
        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        }
        return null;
    }

    /**
     * Part of AsynTask implementation
     * Executed after AsynTask finishes
     * @param aeds Object array that contain all the markers
     */
    @Override
    protected void onPostExecute(Aed[] aeds) {
        if (callback != null && aeds != null) {
            callback.onPostExecuteAedTask(aeds);
        }
    }

    /**
     * Parses a JSON file to get relevant information
     * @param results String of the JSON
     * @return aeds Object array that contain all the markers
     */
    private Aed[] parseJSON(String results){

        JSONArray features = null;
        Aed[] aeds = null;

        try {
            JSONObject aedObject = new JSONObject(results);
            features = aedObject.getJSONArray("features");
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
        }

        if (features != null) {
            aeds = new Aed[features.length()];
            int counter = 0;
            for (int i = 0; i < features.length(); i++) {
                try {
                    JSONObject feature = features.getJSONObject(i);
                    JSONObject properties = feature.getJSONObject("properties");

                    double lat = Double.valueOf(properties.getString("Latitude"));
                    double lng = Double.valueOf(properties.getString("Longtitude"));
                    String name = properties.getString("ADDRESSBUILDINGNAME");
                    String address = properties.getString("Name");;
                    String postalCode = "\nS" + properties.getString("ADDRESSPOSTALCODE");

                    aeds[counter] = new Aed(lat, lng, name, address, postalCode);
                    counter++;

                } catch (JSONException e) {
                    Log.e(TAG,String.valueOf(e));
                }

            }
        }

        return aeds;
    }

    /**
     * Interface of TaskCallBack
     * Implementation of onPreExecute_Task and
     * Implementation of onPostExecute_Task has been
     * implemented here
     */
    public interface AedTaskCallback {
        void onPreExecuteAedTask();

        void onPostExecuteAedTask(Aed[] aeds);
    }

}
