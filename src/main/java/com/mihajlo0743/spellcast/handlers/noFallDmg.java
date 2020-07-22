package com.mihajlo0743.spellcast.handlers;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class noFallDmg {
    @SubscribeEvent
    public void onFall(LivingFallEvent event){
        if (event.getEntity() instanceof PlayerEntity){
            event.setCanceled(true);
        }
    }
}
