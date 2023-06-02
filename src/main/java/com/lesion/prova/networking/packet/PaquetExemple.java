package com.lesion.prova.networking.packet;

import com.lesion.prova.networking.ModMessages;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class PaquetExemple {
    public PaquetExemple(){

    }
    public PaquetExemple(FriendlyByteBuf buf){

    }
    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            Collection<MobEffectInstance> efectos = player.getActiveEffects();
            if(efectos.size() == 0) {
                player.displayClientMessage(Component.literal("No tens efectos"), true);
            }else{
                AtomicBoolean nose = new AtomicBoolean(false);
                player.getCapability(ProveidorPropietatJugador.PROPIETATJUGADOR).ifPresent(propietat ->{
                    if(propietat.obtenirPropietat() == 0){
                        nose.set(true);
                    }
                    propietat.restarPropietat(1);
                    ModMessages.sendToPlayer(new SincronitzacioPropietatS2C(propietat.obtenirPropietat()), player);

                });
                if(!nose.get()){
                    String efectostotals = "";
                    MobEffect efecto = efectos.iterator().next().getEffect();
                    int vueltas = efectos.size();
                    int cogidos = 1;
                    for (int i = 0; i < vueltas; i++){


                        int duracion = player.getEffect(efecto).getDuration();
                        player.removeEffect(efecto);
                        MobEffect proximo = null;
                        Iterator<MobEffectInstance> iterador = efectos.iterator();
                        if(i < vueltas-1){
                            for(int j = 0; j < cogidos; j++){
                                proximo = iterador.next().getEffect();
                            }
                        }
                        cogidos++;
                        MobEffectInstance instancia = new MobEffectInstance(efecto, duracion*2);
                        player.addEffect(instancia);

                        efectostotals = efectostotals + efecto.getDescriptionId().replaceAll("effect.minecraft", "").trim();
                        efecto = proximo;
                    }

                }else{
                    player.displayClientMessage(Component.literal("No tens poder suficient"), true);
                }


                //player.displayClientMessage(Component.literal("Si tens efectos, " + efectostotals), true);

            }

            EntityType.COW.spawn(level, null, null, player.blockPosition(),
                    MobSpawnType.COMMAND, true, false);
        });
        return true;
    }
}
