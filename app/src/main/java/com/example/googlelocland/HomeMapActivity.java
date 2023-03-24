package com.example.googlelocland;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlelocland.databinding.ActivityHomeMap2Binding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener
{

    private double lat, longi;
    private GoogleMap mMap;
    private ActivityHomeMap2Binding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    ImageButton hosp,atm, museum, rest, park;
    Button toButton;
    double endLatitude, endLongitude;
   //widgets
    private EditText mSearchtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeMap2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mSearchtxt = (EditText) findViewById(R.id.location_search);
        hosp=findViewById(R.id.hospital);
        atm=findViewById(R.id.atm);
        museum=findViewById(R.id.museum);
        rest=findViewById(R.id.res);
        park = findViewById(R.id.parks);
        toButton = findViewById(R.id.btnTo);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this.getApplicationContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        atm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //https://maps.googleapis.com/maps/api/place/nearbysearch/json?
                StringBuilder stringBuilder=new StringBuilder("https://maps-googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat+", "+ longi);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=atm");
                stringBuilder.append("&sensor=true");
                //stringBuilder.append("&key="+ getResources().getString(R.id.google_maps_key));

                String url = stringBuilder.toString();
                Object datafetch[]=new Object[2];
                datafetch[0]= mMap;
                datafetch[1]=url;

                FetchInfo fetchInfo=new FetchInfo();
                fetchInfo.execute(datafetch);
            }
        });

        hosp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder=new StringBuilder("https://maps-googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat+", "+ longi);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=hospital");
                stringBuilder.append("&sensor=true");
                //stringBuilder.append("&key="+ getResources().getString(R.id.google_maps_key));

                String url = stringBuilder.toString();
                Object datafetch[]=new Object[2];
                datafetch[0]= mMap;
                datafetch[1]=url;

                FetchInfo fetchInfo=new FetchInfo();
                fetchInfo.execute(datafetch);
            }
        });

        museum.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder=new StringBuilder("https://maps-googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat+", "+ longi);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=museum");
                stringBuilder.append("&sensor=true");
                //stringBuilder.append("&key="+ getResources().getString(R.id.google_maps_key));

                String url = stringBuilder.toString();
                Object datafetch[]=new Object[2];
                datafetch[0]= mMap;
                datafetch[1]=url;

                FetchInfo fetchInfo=new FetchInfo();
                fetchInfo.execute(datafetch);
            }
        });

        rest.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder=new StringBuilder("https://maps-googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat+", "+ longi);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=restaurant");
                stringBuilder.append("&sensor=true");
                //stringBuilder.append("&key="+ getResources().getString(R.id.google_maps_key));

                String url = stringBuilder.toString();
                Object datafetch[]=new Object[2];
                datafetch[0]= mMap;
                datafetch[1]=url;

                FetchInfo fetchInfo=new FetchInfo();
                fetchInfo.execute(datafetch);
            }
        });
        park.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder=new StringBuilder("https://maps-googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat+", "+ longi);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=park");
                stringBuilder.append("&sensor=true");
                //stringBuilder.append("&key="+ getResources().getString(R.id.google_maps_key));

                String url = stringBuilder.toString();
                Object datafetch[]=new Object[2];
                datafetch[0]= mMap;
                datafetch[1]=url;

                FetchInfo fetchInfo=new FetchInfo();
                fetchInfo.execute(datafetch);
            }
        });
        //if the to button is clicked
        toButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mMap.clear();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(endLatitude, endLongitude));
                markerOptions.title("Destination");
                markerOptions.draggable(true);

                float results[] = new float[10];
                Location.distanceBetween(lat, longi, endLatitude, endLongitude, results);
                markerOptions.snippet("Distance= "+ results[0]);
                mMap.addMarker(markerOptions);
            }
        });
        init();
    }

    private void init(){
        Log.d(TAG, "init: initializing");

        mSearchtxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
                || event.getAction() == KeyEvent.ACTION_DOWN || event.getAction()  == KeyEvent.KEYCODE_ENTER){
                //executing search method
                    geoLocate();
                }
                return false;
            }
        });

        //HideSoftKeyboard();
    }

    //method for locating and positioning camera to searched location
    private void geoLocate() {
        Log.d(TAG, "geoLocate: geoLocating");
        String searchString  = mSearchtxt.getText().toString();

        Geocoder geocoder=new Geocoder(HomeMapActivity.this);
        List<Address> addressList = new ArrayList<>();
        try {
            addressList = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }

        Address address= addressList.get(0);
        if(addressList.size()> 0){
            int zoom = (int) mMap.getCameraPosition().zoom;
            Log.d(TAG, "geoLocate: found Location:" + address.toString());
            Toast.makeText(this,address.toString(), Toast.LENGTH_SHORT).show();
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(address.getLatitude(), address.getLongitude()),15));
            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(address.getLatitude(), address.getLongitude()),zoom));        }
        }

        MarkerOptions options = new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())).
                position(new LatLng(address.getLatitude(), address.getLongitude())).
                title(address.getAddressLine(0));
        mMap.addMarker(options);

        //HideSoftKeyboard();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        getCurrentLocation();
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);

    }

//method returns users current location, this method says "android.permission.ACCESS_COARSE_LOCATION" when ACCESS_FINE_LOCATION permission is already in manifest, this makes it impossible to retrieve users current location"
/*    @RequiresPermission(anyOf = {
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    })*/
    @SuppressLint("MissingPermission")
    public void getCurrentLocation(){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
          ActivityCompat.requestPermissions(this ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
          return;
        }

        LocationRequest locationRequest =  LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);

        //used for receiving notification from fuselocation provider API
        LocationCallback locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult){
                Toast.makeText(getApplicationContext(), "location is= " + locationResult, Toast.LENGTH_LONG).show();

                if(locationResult == null){
                    Toast.makeText(getApplicationContext(), "Current Location is null",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                for (Location location:locationResult.getLocations()){
                    if(location != null){
                        Toast.makeText(getApplicationContext(), "Current location is: "+ location.getLongitude(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    lat = location.getLatitude();
                    longi = location.getLongitude();

                    LatLng latLng = new LatLng(lat, longi);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                }
            }
        });
    }

    private void moveCamera(LatLng latlng,float zoom){
        Log.d(TAG, "moveCamera: moving cameras to lat:"+latlng.latitude + ", lng: "+ latlng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(Request_code){
            case Request_code:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }
        }
    }

    private void HideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    //method allows for the marker on the map to be draggable
    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        marker.setDraggable(true);
        return false;
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        endLatitude = marker.getPosition().latitude;
        endLongitude = marker.getPosition().longitude;
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }
}