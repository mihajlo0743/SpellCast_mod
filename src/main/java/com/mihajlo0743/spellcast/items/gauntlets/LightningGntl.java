package com.mihajlo0743.spellcast.items.gauntlets;

import com.mihajlo0743.spellcast.utils.DmgUtil;
import com.mihajlo0743.spellcast.utils.RayTrace;
import com.mihajlo0743.spellcast.utils.RenderUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class LightningGntl extends Gauntlet {

    public LightningGntl() {
        super(Rarity.COMMON);
        setRegistryName("lightning_gauntlet");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (handIn != Hand.MAIN_HAND)
            return ActionResult.newResult(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
        cast(playerIn);
        return ActionResult.newResult(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }


    @Override
    public void cast(PlayerEntity entity) {
        LivingEntity tracentity = RayTrace.getLookingAt(entity,  500);
        if (tracentity == null) return;
        RenderUtil.SheduleLightningRender(tracentity.getPositionVec());
        DmgUtil.DamagePlayer(tracentity, 4);

    }

}
