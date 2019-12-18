package pmd.di.ubi.ubi5stars;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    // private LocationManager locationManager;
    private int TAG_CODE_PERMISSION_LOCATION = 0;
    private GoogleMap mMap;
    private AdView mAdView;
    private String lastMarkerId = "";

    private static String locationsCollection = "locations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
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


            // Obter posição do utilizador
            // locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            // LatLng currentPosition = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            // mMap.addMarker(new MarkerOptions().position(currentPosition).title("Eu!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

            // Marcadores do Mapa
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280294, -7.505833)).title("Igreja de Santa Maria Maior").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280160, -7.507179)).title("Estátua Abstrata").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.281742, -7.508206)).title("Capela de Santa Cruz").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.277191, -7.497815)).title("Igreja da Santíssima Trindade").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.276770, -7.505400)).title("Jardim do Goldra").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.279229, -7.504675)).title("Miradouro Portas do Sol").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.274700, -7.496663)).title("Jardim do Lago").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.273767, -7.497768)).title("Terminal Rodoviário").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.277396, -7.496232)).title("Estação Caminhos Ferro").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.282644, -7.504344)).title("Igreja Nossa Senhora da Conceição").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280657, -7.503638)).title("Igreja da Misericórdia").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.270755, -7.502699)).title("Serra Shopping").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280436, -7.504633)).title("Câmara Municipal").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280998, -7.504517)).title("Teatro Municipal").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.284435, -7.503398)).title("Elevador do Jardim").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.282798, -7.512258)).title("Estádio Municipal").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.288684, -7.515052)).title("UBI Ciências Socias e Humanas"));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.266043, -7.498634)).title("Complexo Desportivo").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.269033, -7.493721)).title("UBI FCS"));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.278414, -7.511632)).title("UBI Faculdade Engenharia"));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(40.277500868, -7.508651688)).title("UBI Polo 1"));

            getMarkers(mMap);

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (marker.getId().equals(lastMarkerId)) {
                        Intent i = new Intent(MapActivity.this, AboutMarkerActivity.class);
                        String name = marker.getTitle();
                        i.putExtra("locationName", name);
                        startActivity(i);
                        return false;
                    } else {
                        lastMarkerId = marker.getId();
                        return false;
                    }
                }
            });

        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, TAG_CODE_PERMISSION_LOCATION);
        }


    }

    private void getMarkers(final GoogleMap mMap) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(locationsCollection);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    pmd.di.ubi.ubi5stars.Location l = ds.getValue(pmd.di.ubi.ubi5stars.Location.class);
                    float f = 0.0f;
                    switch (l.getCategory()) {
                        // TODO: Add Categories
                        case "Blah":
                            f = 330.0f;
                            break;
                    }
                    if (l.getName().equals("Reitoria")) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(l.getLat(), l.getLon()), 15));
                    } else {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLon())).title(l.getName()).icon(BitmapDescriptorFactory.defaultMarker(f)));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showMenu(View v) {
        startActivity(new Intent(MapActivity.this, MenuActivity.class));
    }

    public void search(View v) {
        // TODO: implement
    }
}
