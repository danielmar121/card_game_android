package com.daniel.card_game_android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daniel.card_game_android.R;
import com.daniel.card_game_android.fragments.FragmentList;
import com.daniel.card_game_android.objects.TopTenRecords;
import com.daniel.card_game_android.services.RecordItemAdapter;
import com.daniel.card_game_android.utils.Constants;
import com.daniel.card_game_android.utils.MyScreenUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import static com.daniel.card_game_android.utils.Constants.TOP_TEN;


public class RecordsPage extends AppCompatActivity implements OnMapReadyCallback {
    private TopTenRecords topTenRecords;

    //map
    private GoogleMap mMap;
    final static String LATITUDE = "LATITUDE";
    final static String LONGITUDE = "LONGITUDE";
    final static String NAME = "NAME";
    double latitude=0.142666, longitude= 51.501024;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_page);

        getTopTen();

        findViews();

        RecordItemAdapter itemAdapter = new RecordItemAdapter(this,
                R.layout.record_item, topTenRecords.getRecords());

        FragmentList fragmentList = new FragmentList(itemAdapter);
        getSupportFragmentManager().beginTransaction().add(R.id.record_LAY_list, fragmentList).commit();

        // map
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitude = getIntent().getDoubleExtra(LATITUDE, 0.0);
        longitude = getIntent().getDoubleExtra(LONGITUDE, 0.0);
        name = getIntent().getStringExtra(NAME);
    }

    private void findViews() {
        ImageView record_IMG_background = findViewById(R.id.record_IMG_background);
        MyScreenUtils.updateBackground(Constants.BACKGROUND_NAME, this, record_IMG_background);
    }

    private void getTopTen() {
        topTenRecords = new TopTenRecords();
        SharedPreferences prefs = getSharedPreferences("MY_SP", MODE_PRIVATE);
        Gson gson = new Gson();

        String jsonFromMemory = prefs.getString(TOP_TEN, "");
        if (!jsonFromMemory.equals("")) {
            topTenRecords = gson.fromJson(jsonFromMemory, TopTenRecords.class);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title(name);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.addMarker(markerOptions);
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(latitude,
                        longitude));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }

/*
    private void insertLocation(int index) {
        Intent intent = new Intent(RecordsPage.this, MapsActivity.class);
        intent.putExtra(MapsActivity.LATITUDE, rec[index].getLocation()[0]);
        intent.putExtra(MapsActivity.LONGITUDE, rec[index].getLocation()[1]);
        intent.putExtra(MapsActivity.NAME, rec[index].getName());
        startActivity(intent);
    }*/
}