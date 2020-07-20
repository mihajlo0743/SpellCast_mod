package com.mihajlo0743.spellcast.capability;

import com.mihajlo0743.spellcast.Spellcast;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RuneCapability implements ICapabilityProvider {

    final LazyOptional<ICurio> capabl;

    public RuneCapability(){
        capabl = LazyOptional.of(() -> new ICurio(){
            @Override
            public boolean canRightClickEquip() {
                return false;
            }

            @Override
            public void onEquipped(String identifier, LivingEntity livingEntity) {

                Spellcast.LOGGER.debug("Equipped, " + livingEntity.getCapability(CuriosCapability.INVENTORY).map((h)->(h.getStackInSlot(identifier, 0))));
            }

            @Nonnull
            @Override
            public DropRule getDropRule(LivingEntity livingEntity) {
                return DropRule.ALWAYS_DROP;
            }

            @Override
            public boolean canEquip(String identifier, LivingEntity livingEntity) {
                return true;
            }

        });
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
         return CuriosCapability.ITEM.orEmpty(cap, capabl);
    }
}
