package com.mihajlo0743.spellcast.items;

import com.mihajlo0743.spellcast.Spellcast;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class Belt extends Item {

    public int Armor;

    public Belt(Rarity rarity, int armor, String name) {
        super(new Properties()
            .group(Spellcast.setup.spellGroup)
            .maxStackSize(1)
            .rarity(rarity)
        );
        setRegistryName(name);
        Armor = armor;
    }
}
