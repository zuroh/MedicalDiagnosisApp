package com.example.medicaldiagnosisapp.utilities;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.example.medicaldiagnosisapp.ApiParser.KmlParser;
import com.example.medicaldiagnosisapp.entity.Poly;

import org.w3c.dom.Document;

import java.io.IOException;
import java.net.MalformedURLException;

public class PolyAsyncTask extends AsyncTask<Void, Void, Poly[]> {

    private static final String TAG = PolyAsyncTask.class.getSimpleName();

    private PolyTaskCallback callback;

    private Context context;

    /**
     * for getting assets
     * @param myContext
     */
    public PolyAsyncTask (PolyTaskCallback callback, Context myContext) {
        this.context = myContext;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.onPreExecutePolyTask();
        }
    }

    @Override
    protected Poly[] doInBackground(Void... voids) {
        try {
            //URL url = new URL(POLY_LOCATION_URL);
            //String result = NetworkUtils.getResponseFromHttpUrl(url);
            Document doc = KmlParser.createDocumentFromKml(context, "polyclinics.kml");
            Log.v("tag", "read json file from assets" );
            return parseJSON(doc);
        } catch (MalformedURLException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Poly[] polys) {
        if (callback != null && polys != null) {
            callback.onPostExecutePolyTask(polys);
        }
    }

    private Poly[] parseJSON(Document doc){

        int iterations = KmlParser.getNoNodes(doc, "Point");
        Poly[] polys = new Poly[iterations];
        String markerInfo = "";

        for (int i = 0; i < iterations; i++) { //replace with iterations
            Location tmpPolyL = KmlParser.getCoordinates(doc, i);
            double lat = Double.valueOf(tmpPolyL.getLatitude());
            double lng = Double.valueOf(tmpPolyL.getLongitude());

            markerInfo = KmlParser.getMarkerInfoP(doc, i);

            polys[i] = new Poly (lat, lng, "Nearest Aed", markerInfo, "");
        }

        return polys;
    }

    public interface PolyTaskCallback {
        void onPreExecutePolyTask();

        void onPostExecutePolyTask(Poly[] polys);
    }

}
