package Fragments;

import android.os.Bundle;

import com.echo.contactsmap.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class CustomMapFragment extends com.google.android.gms.maps.SupportMapFragment {

    GoogleMap googleMap;
    List<LatLng> coords = new ArrayList<>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        googleMap = getMap();
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        //setting features of maps
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);

        //adding markers from contacts
        addMarkers();
    }

    private void addMarkers() {
        for(int i=0; i< MainActivity.contactEntries.size(); i++){
            LatLng ll = new LatLng(MainActivity.contactEntries.get(i).getLat(), MainActivity.contactEntries.get(i).getLonge());
            coords.add(ll);
            String name = MainActivity.contactEntries.get(i).getName();
            MarkerOptions marker = new MarkerOptions().position(ll).title(name);
            googleMap.addMarker(marker);
        }
    }

}