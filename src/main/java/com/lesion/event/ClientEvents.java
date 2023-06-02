package com.lesion.event;

import com.lesion.prova.client.HudPersonalizada;
import com.lesion.prova.networking.ModMessages;
import com.lesion.prova.networking.packet.PaquetExemple;
import com.lesion.prova.prova;
import com.lesion.prova.util.TeclaEspecial;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = prova.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents{

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(TeclaEspecial.MAPEIGTECLAESPECIAL.consumeClick()){
                ModMessages.sendToServer(new PaquetExemple());
            }
        }
    }
    @Mod.EventBusSubscriber(modid = prova.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)

    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(TeclaEspecial.MAPEIGTECLAESPECIAL);
        }
        @SubscribeEvent
        public static void onKeyRegister(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("propietat", HudPersonalizada.HUDPERSONALIZADO);
        }
    }
}
