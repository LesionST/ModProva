package com.lesion.prova.networking.packet;

import com.lesion.prova.client.ClientPropietatData;
import com.lesion.prova.propietat.ProveidorPropietatJugador;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

public class SincronitzacioPropietatS2C {

    private final int propietat;
    public SincronitzacioPropietatS2C(int propietat){
        this.propietat = propietat;
    }
    public SincronitzacioPropietatS2C(FriendlyByteBuf buf){

        this.propietat = buf.readInt();
    }



    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(propietat);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientPropietatData.set(propietat);


        });
        return true;
    }
}
