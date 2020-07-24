package com.mihajlo0743.spellcast.items.runes;

import com.mihajlo0743.spellcast.Spellcast;
import com.mihajlo0743.spellcast.items.Rune;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class SpringstepRune extends Rune {
    public SpringstepRune() {
        super(Rarity.RARE, "springstep_rune");
    }

    @Override
    public boolean acrivate() {
        /*Vec3d motion = Minecraft.getInstance().player.getMotion();
        Minecraft.getInstance().player.move(MoverType.SELF, new Vec3d(motion.x, 5, motion.z));*/
        Spellcast.proxy.getLocalPlayer().addPotionEffect(new EffectInstance(Effects.LEVITATION, 20, 8));
        return true;
    }
}
