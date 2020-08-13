package com.mihajlo0743.spellcast.setup;

import com.mihajlo0743.spellcast.blocks.ModBlocks;
import com.mihajlo0743.spellcast.capability.CapabilityHandler;
import com.mihajlo0743.spellcast.capability.IStats;
import com.mihajlo0743.spellcast.capability.PlayerStats;
import com.mihajlo0743.spellcast.capability.StatsStorage;
import com.mihajlo0743.spellcast.handlers.noFallDmg;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModSetup {

    private static int PacketID = 0;
    //public IStats stats;
    public ItemGroup itemGroup = new ItemGroup("spellcast") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.TECH_BLOCK);
        }
    };
    public ItemGroup spellGroup = new ItemGroup("SpellItems") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.TECH_BLOCK);
        }
    };

    public  void init(){
        CapabilityManager.INSTANCE.register(IStats.class, new StatsStorage(), PlayerStats::new);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new noFallDmg());
        //NetworkHandler.INSTANCE.registerMessage(PacketID++, )
    }
}
