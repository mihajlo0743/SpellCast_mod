package com.mihajlo0743.spellcast.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Tech_block extends Block {
    public Tech_block(){
        super(Properties.create(Material.ANVIL)
            .harvestLevel(2)
            .hardnessAndResistance(0.3f)
            .lightValue(3)
        );
        setRegistryName("tech_block");
    }
}
