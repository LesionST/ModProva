package com.lesion.prova.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class TeclaEspecial {
    public static final String CATEGORIATECLA = "key.category.prova.teclaespecial";
    public static final String ACCIOTECLA = "key.prova.acciotecla";

    public static final KeyMapping MAPEIGTECLAESPECIAL =
            new KeyMapping(ACCIOTECLA, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, CATEGORIATECLA );
}
