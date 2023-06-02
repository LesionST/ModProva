package com.lesion.prova.block.custom;

import com.lesion.prova.block.ProvaBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ProvaBlockStates extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit"); //Definirem la propietat
    public static final IntegerProperty TIPUS = IntegerProperty.create("tipus", 0,5);

    public ProvaBlockStates(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {

        super.onPlace(blockState, level, blockPos, blockState1, b);
        BlockState blockstate = level.getBlockState(blockPos.below());
        if(blockstate.getBlock() instanceof ProvaBlockStates provaBlockStates){
            int valor = blockstate.getValue(TIPUS);
            level.setBlock(blockPos, blockState.setValue(TIPUS, valor), 3);

        }else{
            Block blockabaix = blockstate.getBlock();
            String nom = blockabaix.getName().getString();

            if(nom.equalsIgnoreCase("dirt")){
                level.setBlock(blockPos, blockState.setValue(TIPUS, 1), 3);
            }else if(nom.equalsIgnoreCase("cobblestone")){
                level.setBlock(blockPos, blockState.setValue(TIPUS, 2), 3);
            }else{
                level.setBlock(blockPos, blockState.setValue(TIPUS, 3), 3);
            }
            System.out.println(nom);
        }



    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND){
            level.setBlock(blockPos, blockState.cycle(LIT), 3); //Hem posat que al fet click deret es canvi la propietat booleana
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT); //Aqui afegirem la propietat
        builder.add(TIPUS);
    }
}
