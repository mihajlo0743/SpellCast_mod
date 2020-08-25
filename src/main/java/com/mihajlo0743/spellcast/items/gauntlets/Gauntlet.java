package com.mihajlo0743.spellcast.items.gauntlets;

import com.mihajlo0743.spellcast.Spellcast;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.Hand;

public class Gauntlet extends Item {
    public Gauntlet(Rarity rarity) {
        super(new Properties()
        .maxStackSize(1)
        .group(Spellcast.setup.spellGroup)
        .rarity(rarity)
        );
    }
    public void cast(PlayerEntity player){

    }
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (!(entity.getHeldItem(Hand.OFF_HAND).getItem() instanceof Gauntlet)) return false;
        ((Gauntlet)entity.getHeldItem(Hand.OFF_HAND).getItem()).cast((PlayerEntity) entity);

        return false;
    }
}
