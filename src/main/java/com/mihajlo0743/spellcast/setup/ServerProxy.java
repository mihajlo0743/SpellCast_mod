package com.mihajlo0743.spellcast.setup;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

public class ServerProxy implements IProxy {
    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Cant get client world on server!");
    }

    @Override
    public void init() {

    }

}
