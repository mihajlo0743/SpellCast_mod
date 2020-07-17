package com.mihajlo0743.spellcast.capability;

import com.mihajlo0743.spellcast.Spellcast;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler {

    public static final ResourceLocation STATS_CAP = new ResourceLocation(Spellcast.MODID, "spell_stats");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
        if (!(event.getObject() instanceof PlayerEntity)) return;

        event.addCapability(STATS_CAP, new StatsProvider());
        Spellcast.LOGGER.debug("Stats applied!");
    }
}
