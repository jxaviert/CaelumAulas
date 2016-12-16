package br.com.caelum.listaaluno;

import android.content.Context;
import android.location.Geocoder;

//import com.google.android.gms.identity.intents.Address;

import android.location.Address;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android6523 on 16/12/16.
 */

public class Localizador {
    private Geocoder geo;

    public Localizador(Context ctx){
        geo = new Geocoder(ctx);
    }

    public LatLng getCoordenada(String endereco){

        try {
            List<Address> ends = geo.getFromLocationName(endereco, 1);
            if(!ends.isEmpty()){
                Address end = ends.get(0);

                return new LatLng(end.getLatitude(),end.getLongitude());
            }

        }catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

}
