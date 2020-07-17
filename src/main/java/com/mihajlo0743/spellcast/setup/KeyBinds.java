package com.mihajlo0743.spellcast.setup;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBinds {


    public static KeyBinding[] ModKeyBindings;

    public static void register() {
        ModKeyBindings = new KeyBinding[1]; //Create array

        //Assign all key binds to this array
        ModKeyBindings[0] = new KeyBinding("key.spellcast.rune_action", 340, "key.spellcast.category");


        //Actually register all keys
        for (int i = 0; i < ModKeyBindings.length; ++i) {
            ClientRegistry.registerKeyBinding(ModKeyBindings[i]);
        }
    }
}
