package pmd.di.ubi.ubi5stars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
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

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    private GoogleMap mMap;
    private Location location;
    private AdView mAdView;

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
        mMap.setMyLocationEnabled(true);
        final LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        final Location myLocation;


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
            myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else
            myLocation = null;


        // Obter posição do utilizador

        LatLng currentPosition = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(currentPosition).title("Eu!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


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
        mMap.addMarker(new MarkerOptions().position(new LatLng(40.274850, -7.509027)).title("Reitoria UBI"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(40.277500868,-7.508651688)).title("UBI Polo 1"));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(Map.this,aboutMarker.class);
                startActivity(intent);
                return false;
            }
        });

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 15));

    }

    public void showMenu(View v) {
        startActivity(new Intent(Map.this, Menu.class));
    }

    public void search(View v) {
        // TODO: implement
    }
}
