package pmd.di.ubi.ubi5stars;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

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

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    private GoogleMap mMap;
    private AdView mAdView;
    int TAG_CODE_PERMISSION_LOCATION;
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
            final LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            final Location myLocation;


            myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


            // Obter posição do utilizador

            LatLng currentPosition = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(currentPosition).title("Eu!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

            LatLng reitoria = new LatLng(40.274850, -7.509027);
            mMap.addMarker(new MarkerOptions().position(reitoria).title("Reitoria"));

            // Marcadores do Mapa
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280294, -7.505833)).title("Igreja de Santa Maria Maior").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280160, -7.507179)).title("Estátua Abstrata").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.281742, -7.508206)).title("Capela de Santa Cruz").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.277191, -7.497815)).title("Igreja da Santíssima Trindade").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.276770, -7.505400)).title("Jardim do Goldra").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.279229, -7.504675)).title("Miradouro Portas do Sol").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.274700, -7.496663)).title("Jardim do Lago").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.273767, -7.497768)).title("Terminal Rodoviário").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.277396, -7.496232)).title("Estação Caminhos Ferro").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.282644, -7.504344)).title("Igreja Nossa Senhora da Conceição").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280657, -7.503638)).title("Igreja da Misericórdia").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.270755, -7.502699)).title("Serra Shopping").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280436, -7.504633)).title("Câmara Municipal").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.280998, -7.504517)).title("Teatro Municipal").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.284435, -7.503398)).title("Elevador do Jardim").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.282798, -7.512258)).title("Estádio Municipal").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.288684, -7.515052)).title("UBI Ciências Socias e Humanas"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.266043, -7.498634)).title("Complexo Desportivo").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.269033, -7.493721)).title("UBI FCS"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.278414, -7.511632)).title("UBI Faculdade Engenharia"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(40.277500868,-7.508651688)).title("UBI Polo 1"));

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Intent i = new Intent(MapActivity.this, AboutMarkerActivity.class);
                    String name = marker.getTitle();
                    i.putExtra("locationName", name);
                    startActivity(i);
                    return false;
                }
            });


            map.animateCamera(CameraUpdateFactory.newLatLngZoom(reitoria, 15));
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },TAG_CODE_PERMISSION_LOCATION);
        }



    }

    public void showMenu(View v) {
        startActivity(new Intent(MapActivity.this, MenuActivity.class));
    }

    public void search(View v) {
        // TODO: implement
    }
}
