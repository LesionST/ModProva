package com.lesion.prova;

import com.lesion.prova.networking.ModMessages;
import com.lesion.prova.block.ProvaBlocks;
import com.lesion.prova.item.ProvaItems;
import com.lesion.prova.villager.ProvaAldeanos;
import com.lesion.prova.world.feature.ProvaFeatures;
import com.lesion.prova.world.feature.ProvaPlacedFeatures;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(prova.MOD_ID)
public class prova
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "prova";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public prova()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ProvaItems.register(modEventBus);
        ProvaBlocks.Registrar(modEventBus);

        ProvaAldeanos.register(modEventBus);

        ProvaFeatures.register(modEventBus);
        ProvaPlacedFeatures.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModMessages.register();


            ProvaAldeanos.registerPOIs();
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ProvaBlocks.CULTIVOS.get(), RenderType.cutout());
        }
    }
}
