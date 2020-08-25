package com.mihajlo0743.spellcast.utils;

import javax.annotation.Nullable;

public abstract class SheduledTask {

    public int time;
    public Runnable endTask;
    public Runnable tick;

    public SheduledTask(int time, Runnable end, @Nullable Runnable tick){
        this.time = time;
        endTask = end;
        this.tick = tick;
    }
}
