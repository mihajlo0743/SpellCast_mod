package com.mihajlo0743.spellcast.items.runes;

import com.mihajlo0743.spellcast.items.Rune;
import net.minecraft.item.Rarity;

public class PlaceholderRune extends Rune {
    public PlaceholderRune() {
        super(Rarity.COMMON, "placeholder_rune");
    }

    @Override
    public void acrivate() {
        super.acrivate();
    }



    @Override
    public int getCooldown() {
        return 1;
    }
}
