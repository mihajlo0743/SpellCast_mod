package com.mihajlo0743.spellcast.setup;

import com.mihajlo0743.spellcast.hud.SpellGUI;
import com.mihajlo0743.spellcast.stats.StatHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements IProxy {
    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public void init() {
        KeyBinds.register();
        MinecraftForge.EVENT_BUS.register(new InputHandler());
        MinecraftForge.EVENT_BUS.register(new SpellGUI());
        MinecraftForge.EVENT_BUS.register(new StatHandler());
    }
}
