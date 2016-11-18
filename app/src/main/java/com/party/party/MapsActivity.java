package com.party.party;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /**
     * Mapa da aplicação
     */
    private GoogleMap mMap;

    /**
     * Responsável por disponibilizar a localização do smartphone.smartphone
     */
    private GoogleApiClient mGoogleApiClient;

    /**
     * Guarda a ultima posição do smartphone.
     */
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Vamos instanciar o GoogleApiClient, caso seja nulo
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this) // Interface ConnectionCallbacks
                    .addOnConnectionFailedListener(this) //Interface OnConnectionFailedListener
                    .addApi(LocationServices.API) // Vamos a API do LocationServices
                    .build();
        }
    }

    /*
     * Ao iniciar, connectamos !
     */
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    /*
      * Ao finalizar, desconectamos!
     */
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }


    /*
     * Método invocado quando o GoogleApiClient conseguir se conectar
     */
    @Override
    public void onConnected(Bundle bundle) {
        // pegamos a ultima localização
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            if(mMap != null){
                // Criamos o LatLng através do Location
                final LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                // Adicionamos um Marker com a posição...
                mMap.addMarker(new MarkerOptions().position(latLng).title("Minha Posição"));
                // Um zoom no mapa para a seua posição atual...
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    /*
     * Neste método você deverá tratar caso não consiga se conncetar...
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}