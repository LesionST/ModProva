package com.lesion.prova.propietat;

import net.minecraft.nbt.CompoundTag;

public class PropietatJugador
{
    private int propietat;
    private final int MIN_PROPIETAT = 0;
    private final int MAX_PROPIETAT = 10;

    //Metodes basics
    public int obtenirPropietat(){
        return propietat;
    }

    public void sumarPropietat(int suma){
        this.propietat = Math.min(propietat + suma, MAX_PROPIETAT);
    }

    public void restarPropietat(int resta){
        this.propietat = Math.max(propietat - resta, MIN_PROPIETAT);
    }

    public void copiarPropietat(PropietatJugador clase){
        this.propietat = clase.propietat;
    }

    //Metodes per registrarla i accedirla en el joc
    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("propietat", propietat);
    }
    public void loadNBTData(CompoundTag nbt){
        propietat = nbt.getInt("propietat");
    }
}
