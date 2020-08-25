package com.mihajlo0743.spellcast.utils;

import net.minecraftforge.event.TickEvent;

import java.util.List;

public class Sheduler {
    public static List<SheduledTask> CShedule;
    public static List<SheduledTask> SShedule;

    //@SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event){
        CShedule.forEach(t->{
            t.time--;
            if (t.tick != null)
                t.tick.run();
            if (t.time < 0){
                t.endTask.run();
            }
        });

    }

}
