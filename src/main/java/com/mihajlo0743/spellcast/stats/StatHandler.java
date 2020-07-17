package com.mihajlo0743.spellcast.stats;

import com.mihajlo0743.spellcast.capability.IStats;
import com.mihajlo0743.spellcast.capability.StatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StatHandler  {
    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent tick){
        //if(tick.type != TickEvent.Type.PLAYER) return;

        //TickEvent.PlayerTickEvent
        IStats stats = tick.player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());
        if (stats.getMana()<stats.getMaxMana())
            stats.changeMana(0.8f);
    }

    public static void hover(){
        //IStats stats = Minecraft.getInstance().player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());
        //stats.changeMana(-1.2f);
        Minecraft.getInstance().player.moveVertical = 2;
    }
}
