package com.mihajlo0743.spellcast.items.gauntlets;

import com.mihajlo0743.spellcast.Spellcast;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class Gauntlet extends Item {
    public Gauntlet(Rarity rarity) {
        super(new Properties()
        .maxStackSize(1)
        .group(Spellcast.setup.spellGroup)
        .rarity(rarity)
        );
    }
    public void castOffHand(PlayerEntity player){

    }
}
