package com.mihajlo0743.spellcast.items.gauntlets;

import com.mihajlo0743.spellcast.Spellcast;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LightningGntl extends Gauntlet {
    public LightningGntl() {
        super(Rarity.COMMON);
        setRegistryName("flightning_gauntlet");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (handIn != Hand.MAIN_HAND)
            return ActionResult.newResult(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
        Vec3d dir = playerIn.getLook(1);
        Spellcast.LOGGER.debug(Minecraft.getInstance().objectMouseOver);
            return ActionResult.newResult(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (!(entity.getHeldItem(Hand.OFF_HAND).getItem() instanceof Gauntlet || entity instanceof PlayerEntity)) return false;
        ((Gauntlet)entity.getHeldItem(Hand.OFF_HAND).getItem()).castOffHand((PlayerEntity) entity);

        return false;
    }

    @Override
    public void castOffHand(PlayerEntity entity) {
        Vec3d dir = entity.getForward();

    }
}
