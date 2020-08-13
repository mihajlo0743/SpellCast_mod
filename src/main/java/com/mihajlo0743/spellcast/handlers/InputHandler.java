package com.mihajlo0743.spellcast.handlers;

import com.mihajlo0743.spellcast.hud.SpellGUI;
import com.mihajlo0743.spellcast.items.Rune;
import com.mihajlo0743.spellcast.setup.KeyBinds;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.capability.CuriosCapability;

public class InputHandler {

    public static boolean up = false;
    //public static boolean mh = false;
    //public static boolean oh = false;


    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        //System.out.println(event.getKey());

        Minecraft mc = Minecraft.getInstance();
        if (mc.getConnection() == null)
            return;
        //IStats handlers = Minecraft.getInstance().player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());

        if (KeyBinds.ModKeyBindings[0].isPressed()) //If first key is pressed
        {
            Item stack = mc.player.getCapability(CuriosCapability.INVENTORY).orElse(CuriosCapability.INVENTORY.getDefaultInstance()).getStackInSlot("spellrune", 0).getItem();
            if (stack instanceof Rune){
                if (!((Rune)stack).acrivate()) {
                    SpellGUI.DrawCenteredString("Skill not ready!");
                }
            }
        }
        //mh = KeyBinds.ModKeyBindings[1].isPressed();
        //oh = KeyBinds.ModKeyBindings[2].isPressed();

        GameSettings settings = mc.gameSettings;

        boolean upNow = settings.keyBindJump.isKeyDown();
        if (upNow != up){
            up = upNow;
            //mc.player.setNoGravity(false);
            //mc.player.setMotion(mc.player.getMotion().getX(), 0, mc.player.getMotion().getY());
            StatHandler.refillable = !upNow;
        }

    }

    /*@SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

    }*/
}
