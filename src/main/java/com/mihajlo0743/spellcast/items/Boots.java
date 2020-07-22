package com.mihajlo0743.spellcast.items;

import com.mihajlo0743.spellcast.Spellcast;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class Boots extends Item {
    public int Boost;
    public Boots(Rarity rarity, int boost, String name) {
        super(new Properties()
            .group(Spellcast.setup.spellGroup)
            .maxStackSize(1)
            .rarity(rarity)
        );
        setRegistryName(name);
        Boost = boost;
    }
}
