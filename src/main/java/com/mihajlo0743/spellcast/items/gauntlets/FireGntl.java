package com.mihajlo0743.spellcast.items.gauntlets;

import com.mihajlo0743.spellcast.entity.FlameBall;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireGntl extends Gauntlet {
    public FireGntl() {
        super(Rarity.COMMON);
        setRegistryName("fire_gauntlet");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (handIn != Hand.MAIN_HAND)
            return ActionResult.newResult(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
        cast(playerIn);
        /*Vec3d dir = playerIn.getLook(1);
        FlameBall fireball = new FlameBall(playerIn.world, playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ, dir.getX(), dir.getY(), dir.getZ(), 2f, 30);
        worldIn.addEntity(fireball);*/
            return ActionResult.newResult(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }


    @Override
    public void cast(PlayerEntity entity) {
        Vec3d dir = entity.getForward();
        FlameBall fireball = new FlameBall(entity.world, entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ, dir.getX(), dir.getY(), dir.getZ(), 2f, 30);
        fireball.setInvulnerable(true);
        entity.world.addEntity(fireball);
    }
}
