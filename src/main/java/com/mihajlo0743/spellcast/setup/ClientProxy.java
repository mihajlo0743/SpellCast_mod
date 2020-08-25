package com.mihajlo0743.spellcast.setup;

import com.mihajlo0743.spellcast.handlers.InputHandler;
import com.mihajlo0743.spellcast.handlers.StatHandler;
import com.mihajlo0743.spellcast.hud.SpellGUI;
import com.mihajlo0743.spellcast.utils.RenderUtil;
import com.mihajlo0743.spellcast.utils.Sheduler;
import com.mojang.blaze3d.platform.GlStateManager;
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
        MinecraftForge.EVENT_BUS.register(new Sheduler());
        MinecraftForge.EVENT_BUS.register(new RenderUtil());
        //OBJModel2 model1 = OBJLoader2.INSTANCE.loadModel(new OBJModel2.ModelSettings(new ResourceLocation(Spellcast.MODID, "models/item/fire_gauntlet.obj"), true, false, true, true, "fire_gauntlet.mtl"));

    }

    @Override
    public void drawLightning() {
        GlStateManager.pushMatrix();
        GlStateManager.popMatrix();
    }

}
