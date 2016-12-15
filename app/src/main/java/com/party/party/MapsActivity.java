package com.party.party;

import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(googleServicesAvaible()){
            Toast.makeText(this, "Perfeito!!!!", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_maps);
            initMap();
        }
        else
        {
            //No Google Maps Layout
        }
    }

    private void initMap(){
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

    }

    public boolean googleServicesAvaible(){

        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvaible = api.isGooglePlayServicesAvailable(this);

        if(isAvaible == ConnectionResult.SUCCESS){
            return true;
        }
        else if(api.isUserResolvableError(isAvaible))
        {
            Dialog dialog = api.getErrorDialog(this, isAvaible, 0);
            dialog.show();
        }
        else
        {
            Toast.makeText(this, "Não foi possivel conectar com play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        goToLocationZoom(-19.01833, -49.480405, 15);
    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
    }

    public void geoLocate(View view) throws IOException {
        EditText editText = (EditText) findViewById(R.id.editTextMaps);
        String location = editText.getText().toString();

        Geocoder gc = new Geocoder(this);
        List<android.location.Address> list = gc.getFromLocationName(location, 1);
        android.location.Address address = list.get(0);

        String localizacao = address.getLocality();

        Toast.makeText(this, localizacao, Toast.LENGTH_LONG).show();

        double lat = address.getLatitude();
        double lng = address.getLongitude();
        goToLocationZoom(lat, lng, 15);



    }
}
