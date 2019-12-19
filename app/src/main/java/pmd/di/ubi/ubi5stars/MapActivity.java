package pmd.di.ubi.ubi5stars;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final int FILTER_REQ_CODE = 1;

    private int TAG_CODE_PERMISSION_LOCATION = 0;
    private GoogleMap mMap;
    private AdView mAdView;

    private String lastMarkerId = "";

    private boolean monument;
    private boolean museus;
    private boolean arteUrbana;
    private boolean zonaLazer;
    private boolean zonaComercial;
    private boolean zonaDesportiva;
    private boolean zonaEstudantil;
    private boolean transportes;

    private SharedPreferences sp;

    private static String locationsCollection = "locations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize the Mobile Ads SDK
        MobileAds.initialize(this, getString(R.string.app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);

        // Verify internet connection
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) {
            Toast.makeText(getApplicationContext(),R.string.network_warning,Toast.LENGTH_SHORT).show();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
            mMap.getUiSettings().setRotateGesturesEnabled(true);
            mMap.getUiSettings().setZoomGesturesEnabled(true);

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (marker.getId().equals(lastMarkerId)) {
                        Intent i = new Intent(MapActivity.this, AboutMarkerActivity.class);
                        String name = marker.getTitle();
                        i.putExtra("locationName", name);
                        startActivity(i);
                    } else {
                        lastMarkerId = marker.getId();
                    }
                    return false;
                }
            });

            // set bounds
            setBounds();

            // place markers
            placeMarkers();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, TAG_CODE_PERMISSION_LOCATION);
        }
    }

    private void setBounds() {
        //get latlong for corners for specified place
        LatLng one = new LatLng(40.423003, -7.871293);
        LatLng two = new LatLng(40.115775, -7.143812);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        //add them to builder
        builder.include(one);
        builder.include(two);

        LatLngBounds bounds = builder.build();

        //get width and height to current display screen
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        // 20% padding
        int padding = (int) (width * 0.20);

        //set latlong bounds
        mMap.setLatLngBoundsForCameraTarget(bounds);

        //move camera to fill the bound to screen
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

        //set zoom to level to current so that you won't be able to zoom out viz. move outside bounds
        mMap.setMinZoomPreference(mMap.getCameraPosition().zoom);
    }

    private void placeMarkers() {
        mMap.clear();

        sp = getSharedPreferences("Filters", 0);
        monument = sp.getBoolean("monumentos", true);
        museus = sp.getBoolean("museus", true);
        arteUrbana = sp.getBoolean("arteUrbana", false);
        zonaLazer = sp.getBoolean("zonaLazer", false);
        zonaComercial = sp.getBoolean("zonaComercial", false);
        zonaDesportiva = sp.getBoolean("zonaDesportiva", false);
        zonaEstudantil = sp.getBoolean("zonaEstudantil", false);
        transportes = sp.getBoolean("transportes", false);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(locationsCollection);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LocationCollection l = ds.getValue(LocationCollection.class);

                    float f = 0.0f;
                    switch (l.getCategory()) {
                        case "Monumento":
                            f = BitmapDescriptorFactory.HUE_AZURE;
                            break;
                        case "Museu":
                            f = BitmapDescriptorFactory.HUE_BLUE;
                            break;
                        case "Arte Urbana":
                            f = BitmapDescriptorFactory.HUE_CYAN;
                            break;
                        case "Zona Lazer":
                            f = BitmapDescriptorFactory.HUE_MAGENTA;
                            break;
                        case "Zona Comercial":
                            f = BitmapDescriptorFactory.HUE_ORANGE;
                            break;
                        case "Zona Desportiva":
                            f = BitmapDescriptorFactory.HUE_ROSE;
                            break;
                        case "Zona Estudantil":
                            f = BitmapDescriptorFactory.HUE_VIOLET;
                            break;
                        case "Transporte":
                            f = BitmapDescriptorFactory.HUE_YELLOW;
                            break;
                    }

                    if (l.getName().equals("Polo Principal")) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(l.getLat(), l.getLon()), 15));
                    }

                    if (monument && l.getCategory().equals("Monumento")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }

                    if (museus && l.getCategory().equals("Museu")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }

                    if (arteUrbana && l.getCategory().equals("Arte Urbana")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }

                    if (zonaLazer && l.getCategory().equals("Zona Lazer")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }

                    if (zonaComercial && l.getCategory().equals("Zona Comercial")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }

                    if (zonaDesportiva && l.getCategory().equals("Zona Desportiva")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }

                    if (zonaEstudantil && l.getCategory().equals("Zona Estudantil")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }

                    if (transportes && l.getCategory().equals("Transporte")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MapActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showMenu(View v) {
        Intent i = new Intent(MapActivity.this, MenuActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void filter(View v) {
        Intent i = new Intent(this, FilterActivity.class);
        startActivityForResult(i, FILTER_REQ_CODE);
    }

    public void search(View v) {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }
}
