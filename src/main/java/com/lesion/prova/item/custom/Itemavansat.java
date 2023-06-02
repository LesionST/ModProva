package com.lesion.prova.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;


public class Itemavansat extends Item {


    public Itemavansat(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        if(!level.isClientSide() && interactionHand == interactionHand.OFF_HAND){//Accions en el cantó del server
            Inventory inventari = player.getInventory();
            NonNullList<ItemStack> armadura = inventari.armor;

            Iterator<ItemStack> iterador = armadura.iterator();
            int contadorair = 0;
            while(iterador.hasNext()){
                ItemStack itemactual = iterador.next();
                if(itemactual.getDisplayName().getString().equalsIgnoreCase("[air]")){
                    contadorair++;
                }
            }


            if(contadorair == 4){

                player.sendSystemMessage(Component.literal("Haha no tens armadura")); //Enviar Missatge

            }else{
                player.sendSystemMessage(Component.literal("Ostres, tens armadura :(")); //Enviar Missatge

            }

            player.getCooldowns().addCooldown(this, 10); //Cooldown

        }


        return super.use(level, player, interactionHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown()){ //Mira si te el shift activat, tambe es pot fer amb l'alt entre altres...
            components.add(Component.literal("Es un coraçao").withStyle(ChatFormatting.BLUE)); //Escriu el tooltip como tal
        }else{
            components.add(Component.literal("Me cago en la puta no apretis el shift inutil de merda").withStyle(ChatFormatting.BLUE)); //Sense shift
        }


        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {

        return super.useOn(useOnContext);
    }
}
