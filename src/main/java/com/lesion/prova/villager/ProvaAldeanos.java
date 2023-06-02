package com.lesion.prova.villager;

import com.google.common.collect.ImmutableSet;
import com.lesion.prova.block.ProvaBlocks;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.lesion.prova.prova;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ProvaAldeanos {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, prova.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS,prova.MOD_ID);
    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
    public static final RegistryObject<PoiType> ZIRCONBLOCKPOI = POI_TYPES.register("zircon_block_poi",
            () -> new PoiType(ImmutableSet.copyOf(
                    ProvaBlocks.ZIRON_BLOCK.get().getStateDefinition().getPossibleStates()),1,1));

    public static final RegistryObject<VillagerProfession> ALDEANOPRO = VILLAGER_PROFESSIONS.register("aldeanopro",
            () -> new VillagerProfession("aldeano_pro", x ->  x.get() == ZIRCONBLOCKPOI.get(),
                    x -> x.get() == ZIRCONBLOCKPOI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));

    public static void registerPOIs(){
        try{
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates",PoiType.class).invoke(null, ZIRCONBLOCKPOI.get());
        }catch(InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }

}
