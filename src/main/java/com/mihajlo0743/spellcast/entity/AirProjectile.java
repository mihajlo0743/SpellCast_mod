package com.mihajlo0743.spellcast.entity;

import com.mihajlo0743.spellcast.utils.DmgUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;

public class AirProjectile extends ArrowEntity {
    private int dmg;
    public AirProjectile(LivingEntity shooter, int damage) {
        super(shooter.world, shooter);
        dmg = damage;
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.hitInfo instanceof LivingEntity)
            DmgUtil.DamagePlayer((LivingEntity)result.hitInfo, dmg);
        else world.getEntitiesWithinAABB(/*EntityType.PLAYER, */Entity.class, new AxisAlignedBB(posX,posY,posZ,posX+1,posY+1,posZ+1).grow(2)).forEach(e->{
            if (e.getUniqueID() == shootingEntity) e.move(MoverType.SELF, e.getPositionVec().subtract(result.getHitVec()).mul(4,4,4));

        });
        this.remove();
    }
}
