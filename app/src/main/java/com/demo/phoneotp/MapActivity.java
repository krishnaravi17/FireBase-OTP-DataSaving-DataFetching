package com.demo.phoneotp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng lucknow = new LatLng(26.8467, 80.9462);//sale lucknow ki static location daali h, tu current location pass kar deo
       // googleMap.addMarker(new MarkerOptions().position(lucknow).title("Marker in Sydney"));


        googleMap.addMarker(new MarkerOptions()
                .position(lucknow)
                .title("Lucknow")//sale idhar daal leo naam waam
                .snippet("Population: 4,627,300")//idhar description
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ratty)));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lucknow, 15));//apne zoom wale method laga leo ... animate hokar jata ho jisme

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //idhar kuch aur details
                //agli bar se project bhej dena ...tym waste hota h be pura setup karte me be
                Toast.makeText(MapActivity.this, "CLICKED!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
