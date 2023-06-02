package com.lesion.prova.block.custom;

import com.lesion.prova.item.ProvaItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class Cultivo extends CropBlock {
    public static final IntegerProperty EDAD = IntegerProperty.create("edad", 0, 6);

    public Cultivo(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ProvaItems.SEMILLAS.get();
    }
    @Override
    public IntegerProperty getAgeProperty() {
        return EDAD;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EDAD);
    }

    @Override
    public int getMaxAge() {
        return 6;
    }
}
