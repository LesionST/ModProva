package com.lesion.prova.networking;

import com.lesion.prova.networking.packet.PaquetExemple;
import com.lesion.prova.networking.packet.SincronitzacioPropietatS2C;
import com.lesion.prova.prova;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(prova.MOD_ID, "misatges"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PaquetExemple.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PaquetExemple::new)
                .encoder(PaquetExemple::toBytes)
                .consumerMainThread(PaquetExemple::handle)
                .add();
        net.messageBuilder(SincronitzacioPropietatS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SincronitzacioPropietatS2C::new)
                .encoder(SincronitzacioPropietatS2C::toBytes)
                .consumerMainThread(SincronitzacioPropietatS2C::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG misatge){
        INSTANCE.sendToServer(misatge);
    }

    public static <MSG> void sendToPlayer(MSG misatge, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), misatge);
    }
}
