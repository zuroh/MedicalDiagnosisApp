package com.example.medicaldiagnosisapp.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.medicaldiagnosisapp.apiParser.KmlParser;
import com.example.medicaldiagnosisapp.entities.Poly;

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
            return parseKML(doc);
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

    private Poly[] parseKML(Document doc){

        int iterations = KmlParser.getNoNodes(doc, "Placemark");

        Poly[] polys = new Poly[iterations];
        String markerInfo = "";

        for (int i = 0; i < iterations; i++) {

            double lat = KmlParser.getLatP(doc, i);
            double lng = KmlParser.getLongP(doc, i);

            polys[i] = new Poly (lat, lng,  KmlParser.getMarkerTitleP(doc, i),
                    KmlParser.getMarkerInfoP(doc, i));
            Log.i ("tag", markerInfo + String.valueOf(lat));
        }

        return polys;
    }

    public interface PolyTaskCallback {
        void onPreExecutePolyTask();

        void onPostExecutePolyTask(Poly[] polys);
    }

}
