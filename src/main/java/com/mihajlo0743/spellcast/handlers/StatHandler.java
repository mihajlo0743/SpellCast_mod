package com.mihajlo0743.spellcast.handlers;

import com.mihajlo0743.spellcast.capability.IStats;
import com.mihajlo0743.spellcast.capability.StatsProvider;
import com.mihajlo0743.spellcast.setup.InputHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StatHandler  {
    public static boolean refillable = true;
    private int skipping_ticks = 60;
    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent tick){
        IStats stats = tick.player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());
        if (stats.isLocked() && stats.getMana()<0.5f){
            refillable = false;
            if (skipping_ticks-- == 0) {
                refillable = true;
                skipping_ticks = 60;
            }
        }

        if (stats.getMana()<stats.getMaxMana() && (refillable/* || stats.isLocked()*/)) {
            stats.changeMana(0.6f);
        }

        if (stats.getMana()<1 && !stats.isLocked()) {
            System.out.println(stats.getMaxMana());
            stats.Lock(true); // Lock
        }
        if ( stats.isLocked() && stats.getMana()==stats.getMaxMana()) stats.Lock(false); // Unlock

        if (InputHandler.up && tick.player.fallDistance>0.01f && stats.getMana()>0 && !stats.isLocked()){
            //tick.player.setNoGravity(true);
            stats.changeMana(-1.5f);
            hover(tick.player);

            //tick.player.move(MoverType.SELF, new Vec3d(0, 0.2, 0));
        }
        //refillable = !tick.player.hasNoGravity();
    }

    public static void hover(PlayerEntity player){
        //IStats handlers = Minecraft.getInstance().player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());
        //handlers.changeMana(-1.2f);
        //Minecraft.getInstance().player.move(MoverType.SELF, new Vec3d(0, 0.2, 0));
        Vec3d motion = player.getMotion();
		player.setMotion(motion.getX(), motion.getY()>0 ? 0.14 : motion.getY()+0.07, motion.getZ());
		//player.fallDistance = 1;
    }
}
