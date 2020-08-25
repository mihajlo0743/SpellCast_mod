package com.mihajlo0743.spellcast.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public class DmgUtil {
    // TODO: Change LivingEntity to PlayerEntity... Maybe...
    public static void DamagePlayer(LivingEntity player, float dmg){
        player.attackEntityFrom(DamageSource.MAGIC, dmg);
    }
}
