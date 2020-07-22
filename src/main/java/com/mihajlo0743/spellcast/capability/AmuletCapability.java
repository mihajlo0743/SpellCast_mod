package com.mihajlo0743.spellcast.capability;

import com.mihajlo0743.spellcast.Spellcast;
import com.mihajlo0743.spellcast.items.Amulet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AmuletCapability implements ICapabilityProvider {

    final LazyOptional<ICurio> capabl;

    public AmuletCapability(){
        capabl = LazyOptional.of(() -> new ICurio(){
            @Override
            public boolean canRightClickEquip() {
                return false;
            }

            @Override
            public void onEquipped(String identifier, LivingEntity livingEntity) {
                livingEntity.getCapability(CuriosCapability.INVENTORY).map(h->h.getStackInSlot(identifier, 0)).ifPresent(itemStack -> {
                    if (itemStack.getItem() instanceof Amulet){
                        Amulet amulet = (Amulet) itemStack.getItem();
                        Spellcast.LOGGER.debug("Equipped: "+amulet.getMana());
                        IStats stats = livingEntity.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());
                        stats.setMaxMana(stats.getMaxMana() + amulet.getMana());
                        Spellcast.LOGGER.debug("Mana: "+stats.getMaxMana()+":"+amulet.getMana()+ ((PlayerEntity)livingEntity).getName().getString());
                    }
                });
            }

            @Override
            public void onUnequipped(String identifier, LivingEntity livingEntity) {
               livingEntity.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance()).revertMana();
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
