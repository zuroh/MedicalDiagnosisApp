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

public class AedAsyncTask extends AsyncTask<Void, Void, Aed[]> {

    private static final String TAG = AedAsyncTask.class.getSimpleName();

    //private final String AED_LOCATION_URL = "https://data.gov.sg/dataset/4b69ea5a-72a7-4c41-b065-e30021a150a6/download";

    private AedTaskCallback callback;

    private Context context;

    /**
     * for getting assets
     * @param myContext
     */
    public AedAsyncTask (AedTaskCallback callback, Context myContext) {
        this.context = myContext;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onPreExecuteAedTask();
        }
    }

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

    @Override
    protected void onPostExecute(Aed[] aeds) {
        if (callback != null && aeds != null) {
            callback.onPostExecuteAedTask(aeds);
        }
    }

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

    public interface AedTaskCallback {
        void onPreExecuteAedTask();

        void onPostExecuteAedTask(Aed[] aeds);
    }

}
