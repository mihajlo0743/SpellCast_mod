package com.mihajlo0743.spellcast.capability;

import com.mihajlo0743.spellcast.items.Rune;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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


            @Nonnull
            @Override
            public DropRule getDropRule(LivingEntity livingEntity) {
                return DropRule.ALWAYS_DROP;
            }

            @Override
            public boolean canEquip(String identifier, LivingEntity livingEntity) {
                return true;
            }

            @Override
            public void onEquipped(String identifier, LivingEntity livingEntity) {
                Item item = livingEntity.getCapability(CuriosCapability.INVENTORY).orElse(CuriosCapability.INVENTORY.getDefaultInstance()).getStackInSlot(identifier, 0).getItem();
                ((PlayerEntity)livingEntity).getCooldownTracker().setCooldown(item, ((Rune)item).getCooldown());
            }
        });
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
         return CuriosCapability.ITEM.orEmpty(cap, capabl);
    }
}
