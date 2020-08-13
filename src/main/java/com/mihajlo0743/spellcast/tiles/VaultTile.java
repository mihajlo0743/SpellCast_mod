package com.mihajlo0743.spellcast.tiles;

import com.mihajlo0743.spellcast.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class VaultTile extends TileEntity {
    public VaultTile() {
        super(TileEntityType.Builder.create(VaultTile::new, ModBlocks.TECH_BLOCK).build(null));
    }

}
