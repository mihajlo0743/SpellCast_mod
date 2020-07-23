package com.mihajlo0743.spellcast.items.runes;

import com.mihajlo0743.spellcast.items.Rune;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Rarity;

public class DashRune extends Rune {
    public DashRune() {
        super( Rarity.EPIC);
        setRegistryName("dash_rune");
    }

    @Override
    public boolean acrivate() {
        Minecraft.getInstance().player.moveForward = 3;
        return true;
    }

}
