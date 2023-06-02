package com.lesion.prova.client;

import com.lesion.prova.prova;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.ResourceBundle;

public class HudPersonalizada {
    private static final ResourceLocation BOTELLACOMPLETA = new ResourceLocation(prova.MOD_ID, "textures/hudpersonalizado/botellacompleta.png");
    private static final ResourceLocation BOTELLAVACIA = new ResourceLocation(prova.MOD_ID, "textures/hudpersonalizado/botellavacia.png");

    public static final IGuiOverlay HUDPERSONALIZADO = ((fui, poseStack, partialTick, width, height) ->{
        int x = width/2;
        int y = height;


        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BOTELLAVACIA);
        for(int i = 0; i < 10; i++){
            GuiComponent.blit(poseStack,x -94 + (i*9), y-54,0,0,
                    12,12,12,12);
        }

        RenderSystem.setShaderTexture(0, BOTELLACOMPLETA);
        for(int i = 0; i < 10; i++){
            if(ClientPropietatData.obtenirPropietatJugador() > i){
                GuiComponent.blit(poseStack,x -94 + (i*9), y-54,0,0,
                        12,12,12,12);

            }else{
                break;
            }

        }

    });
}
