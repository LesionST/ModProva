package com.lesion.prova.block;

import com.lesion.prova.block.custom.BlockAvansat;
import com.lesion.prova.block.custom.Cultivo;
import com.lesion.prova.block.custom.ProvaBlockStates;
import com.lesion.prova.item.BarraCreatiu;
import com.lesion.prova.item.ProvaItems;
import com.lesion.prova.prova;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ProvaBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, prova.MOD_ID);


    public static final RegistryObject<BlockAvansat> ZIRON_BLOCK =
            registrarBlock("zirconblock",  // El nom
                    () -> new BlockAvansat(BlockBehaviour
                            .Properties.of(Material.STONE).strength(6f)
                            .requiresCorrectToolForDrops()), //Els atributs del block(es pot allargar)
                    BarraCreatiu.PROVA_TAB); //La barra del creatiu on estara
    public static final RegistryObject<BlockAvansat> BLOCKCANVIA_ORE =
            registrarBlock("blockcanvia_ore",  // El nom
                    () -> new BlockAvansat(BlockBehaviour
                            .Properties.of(Material.STONE).strength(6f)
                            .requiresCorrectToolForDrops()), //Els atributs del block(es pot allargar)
                    BarraCreatiu.PROVA_TAB); //La barra del creatiu on estara


    public static final RegistryObject<ProvaBlockStates> BLOCKCANVIA =
            registrarBlock("blockcanvia",  // El nom
                    () -> new ProvaBlockStates(BlockBehaviour
                            .Properties.of(Material.STONE).strength(6f)
                            .requiresCorrectToolForDrops()
                            .noCollission()), //Els atributs del block(es pot allargar)
                    BarraCreatiu.PROVA_TAB); //La barra del creatiu on estara

    public static final RegistryObject<Cultivo> CULTIVOS =
            BLOCKS.register("cultivos",  // El nom
                    () -> new Cultivo(BlockBehaviour.Properties.copy(Blocks.WHEAT)));  //Perque el cultiu es vegi transparent

    //Metodes per registrar blocks

    //Metode per registrar el block com a tal
    private static <T extends Block> RegistryObject<T> registrarBlock(String name, Supplier<T> block, CreativeModeTab tab){
        //Crearem la instancia registryobject que retornarem
        RegistryObject<T> objecteregistrat = BLOCKS.register(name, block);
        //Utilitzarem la instancia que hem creat per registrar el item del block que estem creant
        registrarItemdeBlock(name, objecteregistrat, tab);
        //Finalment retornarem al instancia
        return objecteregistrat;
    }

    //Metode per regstrar el item del block
    private  static  <T extends Block> RegistryObject<BlockItem> registrarItemdeBlock(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ProvaItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void Registrar(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }


}
