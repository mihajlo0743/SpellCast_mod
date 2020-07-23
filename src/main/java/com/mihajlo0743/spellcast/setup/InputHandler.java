package com.mihajlo0743.spellcast.setup;

import com.mihajlo0743.spellcast.Spellcast;
import com.mihajlo0743.spellcast.capability.IStats;
import com.mihajlo0743.spellcast.capability.StatsProvider;
import com.mihajlo0743.spellcast.handlers.StatHandler;
import com.mihajlo0743.spellcast.hud.SpellGUI;
import com.mihajlo0743.spellcast.items.Rune;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.capability.CuriosCapability;

public class InputHandler {

    public static boolean up = false;


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
            } else return;
        }


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
