package com.example.backgroundtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import com.factual.engine.FactualEngine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isRequiredPermissionAvailable()) {
            initializeEngine();
        } else {
            requestLocationPermissions();
        }
    }

    public void initializeEngine() {
        FactualEngine.initialize(
                this,
                "YOUR API KEY", // TODO: add your API key
                ConsoleLoggingFactualClientReceiver.class);
    }

    // example permissions boilerplate
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (isRequiredPermissionAvailable()) {
            initializeEngine();
        } else {
            Log.e("engine", "Necessary permissions were never provided.");
        }
    }

    public boolean isRequiredPermissionAvailable(){
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestLocationPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },
                0);
    }

}
