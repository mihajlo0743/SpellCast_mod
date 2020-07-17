package com.mihajlo0743.spellcast.setup;

import com.mihajlo0743.spellcast.capability.IStats;
import com.mihajlo0743.spellcast.capability.StatsProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InputHandler {
    @SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		//System.out.println(event.getKey());

        if (KeyBinds.ModKeyBindings[0].isPressed()) //If first key is pressed
        {
        	//System.out.println("KEY IS PRESSED");
        }
        if (Minecraft.getInstance().gameSettings.keyBindJump.isPressed()){
            IStats stats = Minecraft.getInstance().player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());
            stats.changeMana(-1);
        }
        
    }
}
