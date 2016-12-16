package br.com.caelum.listaaluno;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by android6523 on 16/12/16.
 */

public class Configurador implements GoogleApiClient.ConnectionCallbacks {

    private AtualizadorDeLocalizacao atualizador;


    public Configurador(AtualizadorDeLocalizacao atualizador){
        this.atualizador = atualizador;
    }

    @Override
    public void onConnected(Bundle bundle){

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


}
