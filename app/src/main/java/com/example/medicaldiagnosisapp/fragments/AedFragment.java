<<<<<<< Updated upstream
package com.example.medicaldiagnosisapp.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medicaldiagnosisapp.ApiParser.GPS;
import com.example.medicaldiagnosisapp.ApiParser.KmlParser;
import com.example.medicaldiagnosisapp.IGPSActivity;
import com.example.medicaldiagnosisapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Document;

public class AedFragment extends Fragment implements IGPSActivity {

    private Location currentLocation = new Location (LocationManager.GPS_PROVIDER);
    private Location nearestL = new Location(LocationManager.GPS_PROVIDER);
    private float shortestDistanceInMeters;
    private GPS gps;

    public AedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gps = new GPS(this, getActivity());
    }

    @Override
    public void onResume() {
        if (!gps.isRunning()) gps.resumeGPS();
        super.onResume();
    }

    @Override
    public void onStop() {
        // Disconnecting the client invalidates it.
        Log.i("FieldLayout_StartAct", "onStop called. Disconnecting GPS client");
        gps.stopGPS();
        super.onStop();
    }

    @Override
    public void locationChanged(double longitude, double latitude) {
        Log.i("FieldLayout_StartAct", "locationChanged");
        currentLocation.setLatitude(latitude);
        currentLocation.setLongitude(longitude);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_aed, container, false);

        SupportMapFragment AedFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg_aed);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment

        AedFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                //intantiate vars for searching
                float distanceInMeters = 1000;
                int nodeIndex =0;
                String markerInfo = "";

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()))
                        .zoom(17)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                //find nearest aed
                try {
                    Document doc = KmlParser.createDocumentFromKml(getActivity(), "aedVerified.kml");
                    int iterations = KmlParser.getNoNodes(doc, "Point");
                    for (int i = 0; i < iterations; i++) { //replace with iterations
                        Location tmpAedL = KmlParser.getCoordinates(doc, i);
                        float tmpDist = currentLocation.distanceTo(tmpAedL);
                        if (tmpDist < distanceInMeters) {
                            distanceInMeters = tmpDist;
                            nearestL = tmpAedL;

                            shortestDistanceInMeters = distanceInMeters;
                            nodeIndex = i;
                        }
                    }
                    for (int j = 0; j < 7; j++) {
                        markerInfo += KmlParser.getMarkerInfoName(doc, nodeIndex, j) + "\n";
                        markerInfo += KmlParser.getMarkerInfoValue(doc, nodeIndex, j) + "\n";
                    }

                } catch (Exception e) {e.printStackTrace();}

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))
                        .title("Current Location")
                        .snippet("You are currently " + String.valueOf(shortestDistanceInMeters)+ " metres\naway from the nearest AED!"));


                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(nearestL.getLatitude(), nearestL.getLongitude()))
                        .title("Nearest Aed")
                        .snippet(markerInfo)
                        .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.aed)));//got from icon8 open source

                //gmaps marker options
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LinearLayout info = new LinearLayout(getActivity());
                        info.setOrientation(LinearLayout.VERTICAL);

                        TextView title = new TextView(getActivity());
                        title.setTextColor(Color.BLACK);
                        title.setGravity(Gravity.CENTER);
                        title.setTypeface(null, Typeface.BOLD);
                        title.setText(marker.getTitle());

                        TextView snippet = new TextView(getActivity());
                        snippet.setTextColor(Color.GRAY);
                        snippet.setText(marker.getSnippet());

                        info.addView(title);
                        info.addView(snippet);

                        return info;
                    }

                });
            }
        });

        return rootView;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}
=======
//package com.example.medicaldiagnosisapp.fragments;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.graphics.drawable.Drawable;
//import android.location.Location;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.ContextCompat;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.medicaldiagnosisapp.ApiParser.GPS;
//import com.example.medicaldiagnosisapp.ApiParser.JsonParse;
//import com.example.medicaldiagnosisapp.IGPSActivity;
//import com.example.medicaldiagnosisapp.R;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptor;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.gson.Gson;
//
//import java.io.IOException;
////not being used atm.
//public class AedFragment extends Fragment implements IGPSActivity {
//
//    private Location currentLocation = new Location (LocationManager.GPS_PROVIDER);
//    private Location nearestL = new Location(LocationManager.GPS_PROVIDER);
//    private float shortestDistanceInMeters;
//    private GPS gps;
//
//    public AedFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        gps = new GPS(this, getActivity());
//    }
//
//    @Override
//    public void onResume() {
//        if (!gps.isRunning()) gps.resumeGPS();
//        super.onResume();
//    }
//
//    @Override
//    public void onStop() {
//        // Disconnecting the client invalidates it.
//        Log.i("FieldLayout_StartAct", "onStop called. Disconnecting GPS client");
//        gps.stopGPS();
//        super.onStop();
//    }
//
//    @Override
//    public void locationChanged(double longitude, double latitude) {
//        Log.i("FieldLayout_StartAct", "locationChanged");
//        currentLocation.setLatitude(latitude);
//        currentLocation.setLongitude(longitude);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_aed, container, false);
//
//        SupportMapFragment AedFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg_aed);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
//
//        AedFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap mMap) {
//                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
//                mMap.clear(); //clear old markers
//
//                //intantiate vars for searching
//                float distanceInMeters = 1000;
//                int nodeIndex =0;
//                String markerInfo = "";
//
//                CameraPosition googlePlex = CameraPosition.builder()
//                        .target(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()))
//                        .zoom(17)
//                        .bearing(0)
//                        .tilt(45)
//                        .build();
//
//                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 100, null);
//
//                //find nearest aed
//                /*try {
//                    Document doc = KmlParser.createDocumentFromKml(getActivity(), "aedVerified.kml");
//                    int iterations = KmlParser.getNoNodes(doc, "Point");
//                    for (int i = 0; i < iterations; i++) { //replace with iterations
//                        Location tmpAedL = KmlParser.getCoordinates(doc, i);
//                        float tmpDist = currentLocation.distanceTo(tmpAedL);
//                        if (tmpDist < distanceInMeters) {
//                            distanceInMeters = tmpDist;
//                            nearestL = tmpAedL;
//
//                            shortestDistanceInMeters = distanceInMeters;
//                            nodeIndex = i;
//                        }
//                    }
//                    for (int j = 0; j < 7; j++) {
//                        markerInfo += KmlParser.getMarkerInfoName(doc, nodeIndex, j) + "\n";
//                        markerInfo += KmlParser.getMarkerInfoValue(doc, nodeIndex, j) + "\n";
//                    }
//
//                } catch (Exception e) {e.printStackTrace();}*/
//
//                try {
//                    String myJson= (String) JsonParse.inputStreamToString(getContext().getAssets().open("aedVerifiedMini1.txt"));
//                    AedModel aedModel = new Gson().fromJson(myJson, AedModel.class);
//                    Toast toast1 = Toast.makeText(getActivity(), aedModel.aeds.get(0).name, Toast.LENGTH_SHORT);
//                    toast1.show();
//
//                    double longi = currentLocation.getLongitude();
//                    binarySearch(aedModel, 0, aedModel.aeds.size(), currentLocation.getLongitude());
//
//                    //parse(myJson);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))
//                        .title("Current Location")
//                        .snippet("You are currently " + String.valueOf(shortestDistanceInMeters)+ " metres\naway from the nearest AED!"));
//
//
//                mMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(nearestL.getLatitude(), nearestL.getLongitude()))
//                        .title("Nearest Aed")
//                        .snippet(markerInfo)
//                        .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.aed)));//got from icon8 open source
//
//                //gmaps marker options
//                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//
//                    @Override
//                    public View getInfoWindow(Marker arg0) {
//                        return null;
//                    }
//
//                    @Override
//                    public View getInfoContents(Marker marker) {
//
//                        LinearLayout info = new LinearLayout(getActivity());
//                        info.setOrientation(LinearLayout.VERTICAL);
//
//                        TextView title = new TextView(getActivity());
//                        title.setTextColor(Color.BLACK);
//                        title.setGravity(Gravity.CENTER);
//                        title.setTypeface(null, Typeface.BOLD);
//                        title.setText(marker.getTitle());
//
//                        TextView snippet = new TextView(getActivity());
//                        snippet.setTextColor(Color.GRAY);
//                        snippet.setText(marker.getSnippet());
//
//                        info.addView(title);
//                        info.addView(snippet);
//
//                        return info;
//                    }
//
//                });
//            }
//        });
//
//        return rootView;
//    }
//
//    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
//        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
//        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
//        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        vectorDrawable.draw(canvas);
//        return BitmapDescriptorFactory.fromBitmap(bitmap);
//    }
//
//    public int binarySearch(AedModel aedModel, int l, int r, double x)
//    {
//        if (r >= l) {
//            int mid = l + (r - l) / 2;
//
//            // If the element is present at the
//            // middle itself
//            double tmpLong = getLongSerial(aedModel,mid);
//            if (tmpLong == x)
//                return mid;
//
//            // If element is smaller than mid, then
//            // it can only be present in left subarray
//            if (tmpLong > x)
//                return binarySearch(aedModel, l, mid - 1, x);
//
//            // Else the element can only be present
//            // in right subarray
//            return binarySearch(aedModel, mid + 1, r, x);
//        }
//
//        // We reach here when element is not present
//        // in array
//        return -1;
//    }
//
//    public double getLongSerial (AedModel aedModel, int index) {
//        String stringCoord = aedModel.aeds.get(index).coord;
//
//        String [] parts = stringCoord.split(",");
//        double lon = Double.valueOf(parts[0]);
//
//        return lon;
//    }
//
///*    public String parse (String jsonLine) {
//        JsonArray jarray = (JsonArray) new JsonParser().parse(jsonLine);
//
//        String tmp = String.valueOf(((JsonObject)jarray.get(0)).get("coordinates"));
//        String [] parts = tmp.split(",");
//        aed.set(Double.valueOf(parts[0]));
//        double lat = Double.valueOf(parts[1]);
//        Location loc = new Location(LocationManager.GPS_PROVIDER);
//        loc.setLongitude(lon); loc.setLatitude(lat);
//
//        return String a;
//    }*/
//
//}
>>>>>>> Stashed changes
