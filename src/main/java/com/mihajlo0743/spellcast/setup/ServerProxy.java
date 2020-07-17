package com.mihajlo0743.spellcast.setup;

import net.minecraft.world.World;

public class ServerProxy implements IProxy {
    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Cant get client world on server!");
    }

    @Override
    public void init() {

    }
}
