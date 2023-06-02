package com.lesion.prova.client;

public class ClientPropietatData {

    private static int propietatJugador;
    public static void set(int propietat){
        ClientPropietatData.propietatJugador = propietat;
    }

    public static int obtenirPropietatJugador(){
        return propietatJugador;
    }
}
