package com.daniel.card_game_android.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import com.daniel.card_game_android.MapsActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import com.daniel.card_game_android.R;
import com.daniel.card_game_android.utils.Constants;
import com.daniel.card_game_android.utils.MyScreenUtils;

public class WelcomePage extends AppCompatActivity {
    EditText welcome_INPT_name;
    ImageButton welcome_BTN_boy, welcome_BTN_girl;
    Button welcome_BTN_records;
    static Location currentLocation;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    ImageView welcome_IMG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        findViews();
        initViews();
    }

    private void findViews() {
        welcome_INPT_name = findViewById(R.id.welcome_INPT_name);
        welcome_BTN_boy = findViewById(R.id.welcome_BTN_boy);
        welcome_BTN_girl = findViewById(R.id.welcome_BTN_girl);
        welcome_BTN_records = findViewById(R.id.welcome_BTN_records);
        welcome_IMG_background = findViewById(R.id.welcome_IMG_background);

        MyScreenUtils.updateBackground(Constants.BACKGROUND_NAME, this, welcome_IMG_background);
    }

    private void initViews() {
        welcome_BTN_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("boy");
            }
        });

        welcome_BTN_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("girl");
            }
        });

        welcome_BTN_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecords();
            }
        });
    }

    private void showRecords() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void startGame(String playerGander) {
        String name = welcome_INPT_name.getText().toString();
        if (name.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.PLAYER_GENDER, playerGander);
        intent.putExtra(MainActivity.PLAYER_NAME, name);
        startActivity(intent);
        finish();
    }

    private void mapFunc() {
        //thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(this)
                        .setTitle("Request Location permission")
                        .setMessage("You have to give this permission to access this feature ")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(WelcomePage.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            } else {
                // request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object

                                currentLocation = location;
                                //Toast.makeText(getApplicationContext(), currentLocation.getLatitude()+", "+ currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                                Log.d("pppp",""+currentLocation.getLatitude()+", "+ currentLocation.getLongitude() );

                            }
                        }
                    });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //abc
            }else{

            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapFunc();
    }

    public static Location getCurrentLocation() {
        return currentLocation;
    }
}