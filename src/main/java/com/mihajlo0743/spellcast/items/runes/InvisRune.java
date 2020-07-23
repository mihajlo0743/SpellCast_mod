package com.mihajlo0743.spellcast.items.runes;

import com.mihajlo0743.spellcast.items.Rune;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class InvisRune extends Rune {
    public InvisRune() {
        super(Rarity.RARE, "invis_rune");
    }

    @Override
    public boolean acrivate() {
        Minecraft.getInstance().player.addPotionEffect(new EffectInstance(Effect.get(14), 30,1));
        return true;
    }
}
