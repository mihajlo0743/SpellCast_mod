package com.mihajlo0743.spellcast.items;

import com.mihajlo0743.spellcast.Spellcast;
import com.mihajlo0743.spellcast.capability.AmuletCapability;
import com.mihajlo0743.spellcast.setup.ModSetup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.CuriosAPI;

import javax.annotation.Nullable;

public class Amulet extends Item {

    private int Mana;

    public Amulet(Rarity rarity, int mana, String name) {
        super(new Properties()
        .rarity(rarity)
        .maxStackSize(1)
        .group(Spellcast.setup.itemGroup)
        );
        this.Mana = mana;
        setRegistryName(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return new ActionResult<>(ActionResultType.SUCCESS, CuriosAPI.getCuriosHandler(playerIn).map((h)->{
            ItemStack equipped = h.getStackInSlot("spellamulet", 0);
            h.setStackInSlot("spellamulet", 0, playerIn.getHeldItem(handIn));
            return equipped;
        }).orElse(ItemStack.EMPTY));
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new AmuletCapability();
    }

    public int getMana(){ return Mana;}
}
