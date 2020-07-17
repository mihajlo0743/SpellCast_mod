package com.mihajlo0743.spellcast.capability;

import net.minecraft.nbt.*;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StatsStorage implements Capability.IStorage<IStats> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IStats> capability, IStats instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("shield", instance.getShield());
        nbt.putInt("max_shield", instance.getMaxShld());
        nbt.putInt("hp", instance.getHealth());
        nbt.putInt("mana", instance.getMaxMana());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IStats> capability, IStats instance, Direction side, INBT nbt) {
        CompoundNBT stats = (CompoundNBT)nbt;
        instance.set(
                stats.getInt("shield"),
                stats.getInt("max_shield"),
                stats.getInt("hp"),
                stats.getInt("mana")
        );
    }
}
