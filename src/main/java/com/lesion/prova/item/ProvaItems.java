package com.lesion.prova.item;

import com.lesion.prova.block.ProvaBlocks;
import com.lesion.prova.item.custom.Itemavansat;
import com.lesion.prova.prova;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ProvaItems {

    //Registre deferred
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, prova.MOD_ID);
    //Registre d'objecte com a tal

    public static final RegistryObject<Item> ZIRCON =
            ITEMS.register("zircon",
                    () -> new Item(new Item.Properties().tab(BarraCreatiu.PROVA_TAB)));
    public static final RegistryObject<Itemavansat> CORASON =
            ITEMS.register("corason",
                    () -> new Itemavansat(new Item.Properties().tab(BarraCreatiu.PROVA_TAB)));
    public static final RegistryObject<ItemNameBlockItem> SEMILLAS =
            ITEMS.register("semillas",
                    () -> new ItemNameBlockItem(ProvaBlocks.CULTIVOS.get(),new Item.Properties().tab(BarraCreatiu.PROVA_TAB)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}