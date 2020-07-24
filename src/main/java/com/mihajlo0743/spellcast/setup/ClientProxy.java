package com.mihajlo0743.spellcast.setup;

import com.mihajlo0743.spellcast.handlers.StatHandler;
import com.mihajlo0743.spellcast.hud.SpellGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements IProxy {
    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getLocalPlayer() {
        return Minecraft.getInstance().player;
    }

    @Override
    public void init() {
        KeyBinds.register();
        MinecraftForge.EVENT_BUS.register(new InputHandler());
        MinecraftForge.EVENT_BUS.register(new SpellGUI());
        MinecraftForge.EVENT_BUS.register(new StatHandler());
    }
}
