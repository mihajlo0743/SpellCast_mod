package com.mihajlo0743.spellcast.items.runes;

import com.mihajlo0743.spellcast.Spellcast;
import com.mihajlo0743.spellcast.items.Rune;
import net.minecraft.entity.MoverType;
import net.minecraft.item.Rarity;

public class DashRune extends Rune {
    public DashRune() {
        super( Rarity.EPIC);
        setRegistryName("dash_rune");
    }

    @Override
    public void acrivate() {
        Spellcast.proxy.getLocalPlayer().move(MoverType.SELF, Spellcast.proxy.getLocalPlayer().getLook(1).mul(8,8,8));
        super.acrivate();
    }

    @Override
    public int getCooldown() {
        return 60;
    }

}
