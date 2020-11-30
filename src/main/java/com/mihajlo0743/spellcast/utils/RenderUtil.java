package com.mihajlo0743.spellcast.utils;

import com.mihajlo0743.spellcast.Spellcast;
import com.mihajlo0743.spellcast.items.gauntlets.LightningGntl;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtil {
    private final static ResourceLocation lightning = new ResourceLocation(Spellcast.MODID + ":textures/misc/lightning.png");
    private static boolean l_flag = false;
    private static Vec3d end;

    public static void SheduleLightningRender(Vec3d dist){
        l_flag = true;
        end = dist;
    }
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event){
        //324 75 -955

        if (!l_flag) return;
        //Spellcast.LOGGER.debug("render!");
        List<AbstractClientPlayerEntity> players = Minecraft.getInstance().world.getPlayers();
        PlayerEntity myplayer = Minecraft.getInstance().player;

        for (PlayerEntity player : players) {
            if (player.getDistanceSq(myplayer) > 500)
                continue;

            ItemStack heldItem = player.getHeldItem(Hand.MAIN_HAND);
            if (player.isHandActive() && heldItem.getItem() instanceof LightningGntl) {
                drawLightning(player.getPositionVec(), end, 0,0,0,0,10,90,1,player,event.getPartialTicks());
            }
        }
        //l_flag = false;
    }


    private static void drawLightning(Vec3d from, Vec3d to, double xOffset, double yOffset, double zOffset, float r, float g, float b, float thickness, PlayerEntity player, float ticks) {
        //if (Spellcast.proxy instanceof ServerProxy) return;
        Hand activeHand;
        if (player.getHeldItemMainhand().getItem() instanceof LightningGntl) {
            activeHand = Hand.MAIN_HAND;
        } else if (player.getHeldItemOffhand().getItem() instanceof LightningGntl) {
            activeHand = Hand.OFF_HAND;
        } else {
            return;
        }

        Vec3d playerPos = new Vec3d(TileEntityRendererDispatcher.staticPlayerX, TileEntityRendererDispatcher.staticPlayerY, TileEntityRendererDispatcher.staticPlayerZ);
        double distance = from.subtract(to).length();
        long gameTime = player.world.getGameTime();
        double v = gameTime;
        float additiveThickness = (thickness * 3.5f);
        BufferBuilder wr = Tessellator.getInstance().getBuffer();

        GlStateManager.pushMatrix();
        GlStateManager.enableColorMaterial();
        // This makes it so we don't clip into the world, we're effectively drawing on it
        GlStateManager.disableDepthTest();
        GlStateManager.enableBlend();
        //This makes it so multiplayer doesn't matter which side the player is standing on to see someone elses laser
        GlStateManager.disableCull();
        GlStateManager.enableTexture();

        GlStateManager.translated(-playerPos.getX(), -playerPos.getY(), -playerPos.getZ());
        GlStateManager.translated(from.x, from.y, from.z);
        GlStateManager.rotatef(MathHelper.lerp(ticks, -player.rotationYaw, -player.prevRotationYaw), 0, 1, 0);
        GlStateManager.rotatef(MathHelper.lerp(ticks, player.rotationPitch, player.prevRotationPitch), 1, 0, 0);

        // additive laser beam
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color4f(r, g, b, 0.7f);
        Minecraft.getInstance().getTextureManager().bindTexture(lightning/*laserBeamGlow*/);
        drawLightningBolt(xOffset, yOffset, zOffset, additiveThickness, activeHand, distance, wr, 0.5, 1, ticks);

        // main laser, colored part
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color3f(r, g, b);
        Minecraft.getInstance().getTextureManager().bindTexture(lightning);
        drawLightningBolt(xOffset, yOffset, zOffset, thickness, activeHand, distance, wr, v, v + distance * 1.5, ticks);
        // white core
        GlStateManager.color3f(0, 0.15f, 0.85f);
        Minecraft.getInstance().getTextureManager().bindTexture(lightning/*laserBeam*/);
        drawLightningBolt(xOffset, yOffset, zOffset, thickness / 2, activeHand, distance, wr, v, v + distance * 1.5, ticks);
        GlStateManager.disableColorMaterial();
        GlStateManager.disableTexture();
        GlStateManager.disableBlend();
        GlStateManager.enableDepthTest();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    private static void drawLightningBolt(double xOffset, double yOffset, double zOffset, float thickness, Hand hand, double distance, BufferBuilder wr, double v1, double v2, float ticks) {
        float startXOffset = -0.25f;
        float startYOffset = -.115f;
        float startZOffset = 0.65f;

        ClientPlayerEntity player = Minecraft.getInstance().player;
        float f = (MathHelper.lerp(ticks, player.prevRotationPitch, player.rotationPitch) - MathHelper.lerp(ticks, player.prevRenderArmPitch, player.renderArmPitch));
        float f1 = (MathHelper.lerp(ticks, player.prevRotationYaw, player.rotationYaw) - MathHelper.lerp(ticks, player.prevRenderArmYaw, player.renderArmYaw));
        startXOffset = startXOffset + (f1 / 1000);
        startYOffset = startYOffset + (f / 1000);

        // Support for hand sides remembering to take into account of Skin options
        if( Minecraft.getInstance().gameSettings.mainHand != HandSide.RIGHT )
            hand = hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;

        wr.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        if (hand == Hand.MAIN_HAND) {
            wr.pos(startXOffset, -thickness + startYOffset, startZOffset).tex(1, v1).endVertex();
            wr.pos(xOffset, -thickness + yOffset, distance + zOffset).tex(1, v2).endVertex();
            wr.pos(xOffset, thickness + yOffset, distance + zOffset).tex(0, v2).endVertex();
            wr.pos(startXOffset, thickness + startYOffset, startZOffset).tex(0, v1).endVertex();
        } else {
            startYOffset = -.120f;
            wr.pos(-startXOffset, thickness + startYOffset, startZOffset).tex(0, v1).endVertex();
            wr.pos(xOffset, thickness + yOffset, distance + zOffset).tex(0, v2).endVertex();
            wr.pos(xOffset, -thickness + yOffset, distance + zOffset).tex(1, v2).endVertex();
            wr.pos(-startXOffset, -thickness + startYOffset, startZOffset).tex(1, v1).endVertex();
        }
        Tessellator.getInstance().draw();
    }
}
