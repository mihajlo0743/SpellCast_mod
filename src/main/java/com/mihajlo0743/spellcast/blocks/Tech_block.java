package com.mihajlo0743.spellcast.blocks;

import com.mihajlo0743.spellcast.tiles.VaultTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class Tech_block extends Block {
    public Tech_block(){
        super(Properties.create(Material.ANVIL)
            .harvestLevel(2)
            .hardnessAndResistance(0.3f)
            .lightValue(3)
        );
        setRegistryName("tech_block");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new VaultTile();
    }
}
