package com.mihajlo0743.spellcast.setup;

import com.mihajlo0743.spellcast.handlers.StatHandler;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InputHandler {

    public static boolean up = false;


    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        //System.out.println(event.getKey());
        //IStats handlers = Minecraft.getInstance().player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());

        if (KeyBinds.ModKeyBindings[0].isPressed()) //If first key is pressed
        {
            //System.out.println("KEY IS PRESSED");

        }
        /*if (Minecraft.getInstance().gameSettings.keyBindJump.isPressed() && Minecraft.getInstance().player.isAirBorne) {
            handlers.changeMana(-3);
            Minecraft.getInstance().player.setNoGravity(true);
            StatHandler.hover();
        }
        if (!Minecraft.getInstance().gameSettings.keyBindJump.isPressed())
            Minecraft.getInstance().player.setNoGravity(false);*/

    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.getConnection() == null)
            return;
        GameSettings settings = mc.gameSettings;

        boolean upNow = settings.keyBindJump.isKeyDown();
        if (upNow != up){
            up = upNow;
            //mc.player.setNoGravity(false);
            //mc.player.setMotion(mc.player.getMotion().getX(), 0, mc.player.getMotion().getY());
            StatHandler.refillable = !upNow;
        }
    }
}
