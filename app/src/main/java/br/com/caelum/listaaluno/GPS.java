package br.com.caelum.listaaluno;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by android6523 on 16/12/16.
 */

public class GPS implements GoogleApiClient.ConnectionCallbacks, LocationListener {
    private GoogleApiClient client;
    private MostraAlunosActivity activity;

    public GPS(MostraAlunosActivity activity) {
        this.activity = activity;
        client = new GoogleApiClient.Builder(activity).addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        client.connect();
    }
    @SuppressWarnings({"MissingPermission"})
    public void onConnect(Bundle b){
        LocationRequest request = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000)
                .setSmallestDisplacement(50);

        LocationServices.FusedLocationApi.requestLocationUpdates(client,request,this);


    }

    @Override
    public void onLocationChanged(Location location) {

            activity.centralizaNO(new LatLng(location.getLatitude(), location.getLongitude()));
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
