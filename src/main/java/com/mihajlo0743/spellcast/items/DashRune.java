package com.mihajlo0743.spellcast.items;

import net.minecraft.item.Rarity;

public class DashRune extends Rune {
    public DashRune() {
        super( Rarity.EPIC);
        setRegistryName("dash_rune");
    }

    /*@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        CuriosAPI.getCuriosHandler(Minecraft.getInstance().player).ifPresent(h -> {h.setStackInSlot("spellrune", 0, new DashRune().getDefaultInstance());});
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }*/
}
