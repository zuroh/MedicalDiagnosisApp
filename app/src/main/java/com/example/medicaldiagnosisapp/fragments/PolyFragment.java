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
import org.w3c.dom.NodeList;

public class PolyFragment extends Fragment implements IGPSActivity {

    private Location currentLocation = new Location (LocationManager.GPS_PROVIDER);
    private Location nearestL = new Location(LocationManager.GPS_PROVIDER);
    private float shortestDistanceInMeters;
    private GPS gps;

    public PolyFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_poly, container, false);

        SupportMapFragment PolyFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg_poly);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment

        PolyFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                //intantiate vars for searching
                float distanceInMeters = 2000;
                int nodeIndex =0;
                String markerInfo = "";

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()))
                        .zoom(13)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                //find nearest Poly
                try {
                    Document docP = KmlParser.createDocumentFromKml(getActivity(), "polyclinics.kml");
                    NodeList nListP = docP.getElementsByTagName("Placemark");
                    int iterations = nListP.getLength();
                    for (int i = 0; i < iterations; i++) { //replace with iterations
                        Location tmpPolyL = KmlParser.getCoordinatesP(docP, i);
                        float tmpDist = currentLocation.distanceTo(tmpPolyL);
                        if (tmpDist < distanceInMeters) {
                            distanceInMeters = tmpDist;
                            nearestL = tmpPolyL;

                            shortestDistanceInMeters = distanceInMeters;
                            nodeIndex = i;
                        }
                    }
                    markerInfo = KmlParser.getMarkerInfoP(docP, nodeIndex);
                } catch (Exception e) {e.printStackTrace();}

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))
                        .title("Current Location")
                        .snippet("You are currently " + String.valueOf(shortestDistanceInMeters)+ " metres\naway from the nearest Polyclinic!"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(nearestL.getLatitude(), nearestL.getLongitude()))
                        .title("Nearest Polyclinic")
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