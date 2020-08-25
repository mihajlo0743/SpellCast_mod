package com.mihajlo0743.spellcast.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {
    World getClientWorld();

    PlayerEntity getLocalPlayer();

    void init();

    void drawLightning();
}
