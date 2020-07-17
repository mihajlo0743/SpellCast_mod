package com.mihajlo0743.spellcast.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StatsProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IStats.class)
    public static final Capability<IStats> STATS_CAP = null;
    private LazyOptional<IStats> inst = LazyOptional.of(STATS_CAP::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return STATS_CAP.orEmpty(cap, inst);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) STATS_CAP.getStorage().writeNBT(STATS_CAP, STATS_CAP.getDefaultInstance(), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        STATS_CAP.getStorage().readNBT(STATS_CAP, STATS_CAP.getDefaultInstance(), null, nbt);
    }
}
