package com.mihajlo0743.spellcast.tiles;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class VaultTileRender extends TileEntityRenderer<VaultTile> {
    public VaultTileRender() {
        super();
    }


    @Override
    public void renderTileEntityFast(VaultTile te, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer) {
        GlStateManager.pushMatrix();
        GlStateManager.translated(x, y, z);
        GL11.glBegin(GL_TRIANGLES);
        float stepsize = 0.5f;
        for (int t = 0; t < 2*Math.PI; t+= stepsize) {
            GL11.glVertex2d(Math.sin(t),Math.cos(t));
        }
        ///buffer.addVertexData();
        VertexFormat vf = new VertexFormat();
        //vf.addElement(new VertexFormatElement())
        GlStateManager.popMatrix();
        super.renderTileEntityFast(te, x, y, z, partialTicks, destroyStage, buffer);
    }
}
