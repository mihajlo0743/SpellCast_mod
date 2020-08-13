package com.mihajlo0743.spellcast.items.runes;

import com.mihajlo0743.spellcast.Spellcast;
import com.mihajlo0743.spellcast.items.Rune;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effects;

public class InvisRune extends Rune {
    public InvisRune() {
        super(Rarity.RARE, "invis_rune");
    }

    @Override
    public boolean acrivate() {

            Effects.INVISIBILITY.affectEntity(Spellcast.proxy.getLocalPlayer(), Spellcast.proxy.getLocalPlayer(), Spellcast.proxy.getLocalPlayer(), 1,1);
            //Spellcast.proxy.getLocalPlayer().addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 200));
        return true;
    }
}
