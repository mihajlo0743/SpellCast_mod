package com.mihajlo0743.spellcast.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class FlameBall extends FireballEntity {
    private float radius;
    private float damage;
    public FlameBall(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ, float explosionRadius, float damage) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        radius = explosionRadius;
        this.damage = damage;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
         if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), damage);
            this.applyEnchantments(this.shootingEntity, entity);
         }

         this.world.createExplosion((Entity)null, this.posX, this.posY, this.posZ, radius, false, Explosion.Mode.NONE);
         this.remove();
      }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {

    }
}
