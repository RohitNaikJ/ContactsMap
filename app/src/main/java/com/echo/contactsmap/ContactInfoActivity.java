package com.echo.contactsmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactInfoActivity extends AppCompatActivity {

    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String email = bundle.getString("email");
        long phone = bundle.getLong("phone");
        long oPhone = bundle.getLong("officePhone");
        double lat = bundle.getDouble("latitude");
        double longe = bundle.getDouble("longitude");
        Toast.makeText(ContactInfoActivity.this, lat+":"+longe, Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle(name);

        TextView textView_name = (TextView) findViewById(R.id.info_name);
        TextView textView_phone = (TextView) findViewById(R.id.info_phone);
        TextView textView_ophone = (TextView) findViewById(R.id.info_ophone);
        TextView textView_email = (TextView) findViewById(R.id.info_email);

        textView_name.setText(name);
        textView_phone.setText((phone == 0)?("[Not Provided]"):(phone + ""));
        textView_ophone.setText((oPhone == 0)?("[Not Provided]"):(oPhone + ""));
        textView_email.setText(email);

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.map)).getMap();
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        LatLng LL = new LatLng(lat, longe);
        MarkerOptions marker = new MarkerOptions().position(LL).title(name);
        googleMap.addMarker(marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LL, 15));

    }
}
