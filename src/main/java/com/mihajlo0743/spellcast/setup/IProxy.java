package com.mihajlo0743.spellcast.setup;

import net.minecraft.world.World;

public interface IProxy {
    World getClientWorld();

    void init();
}
