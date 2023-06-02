package com.lesion.prova.propietat;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProveidorPropietatJugador implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PropietatJugador> PROPIETATJUGADOR = CapabilityManager.get(new CapabilityToken<PropietatJugador>() { });
    private PropietatJugador propietat = null;

    private final LazyOptional<PropietatJugador> opcional = LazyOptional.of(this::crearPropietatJugador);

    private PropietatJugador crearPropietatJugador(){
        if(this.propietat == null){
            this.propietat = new PropietatJugador();
        }
        return this.propietat;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PROPIETATJUGADOR){
            return opcional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        crearPropietatJugador().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        crearPropietatJugador().loadNBTData(nbt);
    }
}
