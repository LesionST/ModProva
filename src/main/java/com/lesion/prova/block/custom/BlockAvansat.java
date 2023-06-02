package com.lesion.prova.block.custom;

import com.mojang.authlib.properties.Property;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BlockAvansat extends Block {


    public BlockAvansat(Properties p_49795_) {
        super(p_49795_);
    }
//Diu el nom de l'objecte d'abaix quan es fa click deret
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        BlockState blockstateabaix = level.getBlockState(blockPos.below());
        Block blockabaix = blockstateabaix.getBlock();

        System.out.println(blockabaix.getName().getString());


        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }


//Afegeix l'efecte absortion quan es trapitja
    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if(entity instanceof LivingEntity livingEntity){ //Veure si es una entitat que esta viva(osigui un mob)
            livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 10)); //Posar efecte a jugador mitjançant living entity
        }



        super.stepOn(level, blockPos, blockState, entity);
    }
}
