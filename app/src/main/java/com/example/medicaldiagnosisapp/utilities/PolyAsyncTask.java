package com.example.medicaldiagnosisapp.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.medicaldiagnosisapp.apiParser.KmlParser;
import com.example.medicaldiagnosisapp.entities.Poly;

import org.w3c.dom.Document;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * PolyAsyncTask starts the reading of resources in the background
 * the moment the application begins. Makes resource extracting easier
 * when required by the application.
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class PolyAsyncTask extends AsyncTask<Void, Void, Poly[]> {

    private static final String TAG = PolyAsyncTask.class.getSimpleName();

    private PolyTaskCallback callback;

    private Context context;

    /**
     * In order to get context specific information. e.g. getting assets
     * @param myContext the current instance of the activity
     */
    public PolyAsyncTask (PolyTaskCallback callback, Context myContext) {
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
            callback.onPreExecutePolyTask();
        }
    }

    /**
     * Part of AsynTask implementation
     * Parses the resource file while other activities are running
     * @param voids
     * @return null if successful, error message if exception occurred
     */
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

    /**
     * Part of AsynTask implementation
     * Executed after AsynTask finishes
     * @param polys Object array that contain all the markers
     */
    @Override
    protected void onPostExecute(Poly[] polys) {
        if (callback != null && polys != null) {
            callback.onPostExecutePolyTask(polys);
        }
    }

    /**
     * Parses a KML file to get relevant information
     * @param doc Document of the kml
     * @return polys Object array that contain all the markers
     */
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

    /**
     * Interface of TaskCallBack
     * Implementation of onPreExecute_Task and
     * Implementation of onPostExecute_Task has been
     * implemented here
     */
    public interface PolyTaskCallback {
        void onPreExecutePolyTask();

        void onPostExecutePolyTask(Poly[] polys);
    }

}
