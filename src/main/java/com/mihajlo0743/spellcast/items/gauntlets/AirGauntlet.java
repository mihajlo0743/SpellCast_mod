package com.mihajlo0743.spellcast.items.gauntlets;

import com.mihajlo0743.spellcast.entity.AirProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AirGauntlet extends Gauntlet {
    public AirGauntlet() {
        super(Rarity.RARE);
        setRegistryName("air_gauntlet");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (handIn != Hand.MAIN_HAND)
            return ActionResult.newResult(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
            cast(playerIn);
            return ActionResult.newResult(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void cast(PlayerEntity playerIn) {

        Vec3d dir = playerIn.getLook(1);
        AirProjectile projectile = new AirProjectile(playerIn, 4);
        //SnowballEntity projectile = new SnowballEntity(worldIn, playerIn);
        projectile.shoot(playerIn, playerIn.rotationPitch, playerIn.cameraYaw, 5, 8, 0);
        projectile.setMotion(dir);
        playerIn.world.addEntity(projectile);
    }
}
