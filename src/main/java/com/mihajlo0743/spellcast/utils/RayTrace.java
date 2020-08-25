package com.mihajlo0743.spellcast.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class RayTrace {

    /*public static BlockRayTraceResult getLookingAt(PlayerEntity player, int range) {
        return getLookingAt(player, RayTraceContext.FluidMode.NONE, range);
    }*/

    public static LivingEntity getLookingAt(PlayerEntity player, int range) {
        Vec3d look = player.getLookVec();
        Vec3d start = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ).add(look.mul(1,1,1));
        Vec3d end = new Vec3d(player.posX + look.x * range, player.posY + player.getEyeHeight() + look.y * range, player.posZ + look.z * range);
        RayTraceContext context = new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, player);
        end = player.world.rayTraceBlocks(context).getHitVec();
        LivingEntity result = null;
        float dist = Float.MAX_VALUE;
        double crossp;
        List<Entity> ents = player.world.getEntitiesWithinAABB(/*EntityType.PLAYER, */Entity.class, new AxisAlignedBB(start, end));
        for (Entity entity : ents) {
            crossp = entity.getPositionVec().subtract(start).crossProduct(end.subtract(start)).y;
            if (crossp < 1.5f && crossp > -1.5f && entity.getDistance(player) < dist && entity instanceof LivingEntity) {
                //Spellcast.LOGGER.debug(entity.getPositionVec().subtract(start).crossProduct(end.subtract(start)).y);
                result = (LivingEntity) entity;
                dist = entity.getDistance(player);
            }
        }
        return result;
    }
}
