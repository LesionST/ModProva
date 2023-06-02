package com.lesion.prova.world.feature;

import com.google.common.base.Suppliers;
import com.lesion.prova.block.ProvaBlocks;
import com.lesion.prova.prova;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.core.appender.DefaultErrorHandler;

import java.util.List;
import java.util.function.Supplier;

public class ProvaFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURACIOFEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, prova.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> POSARBLOCKCANVIA =
            Suppliers.memoize(() -> List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ProvaBlocks.BLOCKCANVIA_ORE.get().defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ProvaBlocks.BLOCKCANVIA_ORE.get().defaultBlockState())

            ));

    public static final RegistryObject<ConfiguredFeature<?,?>> BLOCKCANVIA_ORE = CONFIGURACIOFEATURE.register("oreblockcanvia",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(POSARBLOCKCANVIA.get(), 9)));

    public static void register(IEventBus eventBus){
        CONFIGURACIOFEATURE.register(eventBus);
    }
}
