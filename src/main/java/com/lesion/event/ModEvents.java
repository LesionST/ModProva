package com.lesion.event;

import com.lesion.prova.item.ProvaItems;
import com.lesion.prova.networking.ModMessages;
import com.lesion.prova.networking.packet.SincronitzacioPropietatS2C;
import com.lesion.prova.propietat.PropietatJugador;
import com.lesion.prova.propietat.ProveidorPropietatJugador;
import com.lesion.prova.prova;
import com.lesion.prova.villager.ProvaAldeanos;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = prova.MOD_ID)
public class ModEvents {
   @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if(event.getType() == ProvaAldeanos.ALDEANOPRO.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ProvaItems.CORASON.get(), 12);
            int villagerLevel = 1;
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack,10,8,0.02F));
        }
    }
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(ProveidorPropietatJugador.PROPIETATJUGADOR).isPresent()) {
                event.addCapability(new ResourceLocation(prova.MOD_ID, "properties"), new ProveidorPropietatJugador());
            }
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PropietatJugador.class);
    }


    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(ProveidorPropietatJugador.PROPIETATJUGADOR).ifPresent(oldStore -> {
                event.getOriginal().getCapability(ProveidorPropietatJugador.PROPIETATJUGADOR).ifPresent(newStore -> {
                    newStore.copiarPropietat(oldStore);
                });
            });
        }
    }
    public static int contador = 0;
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(ProveidorPropietatJugador.PROPIETATJUGADOR).ifPresent(propietat -> {
                if(contador == 800) { // Once Every 10 Seconds on Avg
                    propietat.sumarPropietat(1);

                    ModMessages.sendToPlayer(new SincronitzacioPropietatS2C(propietat.obtenirPropietat()), (ServerPlayer) event.player);

                    contador = 0;
                }
            });
            contador++;
        }
    }
    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(ProveidorPropietatJugador.PROPIETATJUGADOR).ifPresent(propietat -> {
                    ModMessages.sendToPlayer(new SincronitzacioPropietatS2C(propietat.obtenirPropietat()), player);
                });
            }
        }
    }

}
