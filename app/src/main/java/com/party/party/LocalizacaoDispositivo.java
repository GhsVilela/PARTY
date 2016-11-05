package com.party.party;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class LocalizacaoDispositivo extends AppCompatActivity {

    private TextView txtLatitude;
    private TextView txtLongitude;
    private TextView txtCidade;
    private TextView txtEstado;
    private TextView txtPais;
    private TextView txtRua;

    private Location location;
    private LocationManager locationManager;

    private Address endereco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_dispositivo);
        
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        txtRua = (TextView) findViewById(R.id.txtRua);
        txtCidade = (TextView) findViewById(R.id.txtCidade);
        txtEstado = (TextView) findViewById(R.id.txtEstado);
        txtPais = (TextView) findViewById(R.id.txtPais);
                
        double latitude = 0.0;
        double longitude = 0.0;

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

        }else{
            locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            location =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }
        if(location != null){
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        txtLongitude.setText("Longitude: "+longitude);
        txtLatitude.setText("Latitude: "+latitude);

        try {
            endereco = buscarEndereco(latitude,longitude);

            txtRua.setText("Rua: "+endereco.getThoroughfare());
            txtCidade.setText("Cidade: "+endereco.getLocality());
            txtEstado.setText("Estado: "+endereco.getAdminArea());
            txtPais.setText("Pais..:" + endereco.getCountryName());

        }catch (IOException e){
            Log.i("GPS",e.getMessage());
        }

    }

    private Address buscarEndereco(double latitude, double longitude)throws IOException {
        Geocoder geocoder;
        Address address = null;
        List<Address> addresses;

        geocoder = new Geocoder(getApplicationContext());

        addresses = geocoder.getFromLocation(latitude, longitude,1);
        if(addresses.size()>0){
            address = addresses.get(0);
        }

        return address;
    }
}
