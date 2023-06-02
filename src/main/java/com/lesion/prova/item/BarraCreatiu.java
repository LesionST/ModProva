package com.lesion.prova.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BarraCreatiu {

    public static final CreativeModeTab PROVA_TAB = new CreativeModeTab("provatab"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ProvaItems.ZIRCON.get());
        }
    };
}
