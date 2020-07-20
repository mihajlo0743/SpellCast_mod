package com.mihajlo0743.spellcast.hud;

import com.mihajlo0743.spellcast.Spellcast;
import com.mihajlo0743.spellcast.capability.IStats;
import com.mihajlo0743.spellcast.capability.StatsProvider;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.capability.ICurio;

import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;

public class SpellGUI {

    private IngameGui gui = Minecraft.getInstance().ingameGUI;
    private Minecraft mc = Minecraft.getInstance();
    private CuriosAPI curios = new CuriosAPI();

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onOverlay(RenderGameOverlayEvent.Pre event){
        if (event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
            gui.getFontRenderer().drawString("Spellcast mod | Alpha ", 5,3, BossInfo.Color.WHITE.getFormatting().getColor());
            gui.getFontRenderer().drawString(Spellcast.VERSION+" - Not final",5,gui.getFontRenderer().FONT_HEIGHT+3, BossInfo.Color.WHITE.getFormatting().getColor());
        //GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
            int left = mc.mainWindow.getScaledWidth()  /*+ 91*/;
            int top = mc.mainWindow.getScaledHeight();
            //  Drawing Slots
            Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("spellcast:hud/hud-texture.png"/*"textures/item/dash_rune.png"*/));
            for (byte i=1; i<=3; i++) {
                gui.blit(left - i*25 - 15, top - 25, 31, 0, 21, 21);

            }
            gui.blit(left - 71, top - 61, 0, 0, 32,32);
            //  Drawing mana
            IStats stats = mc.player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());
            if ((int)stats.getMana() < stats.getMaxMana()){
                gui.blit(left/2 - 50, top/2 + 15, 0, 32, 100, 7);
                gui.blit(left/2 - 50, top/2 + 15, 0, 38, (int)((stats.getMana()/stats.getMaxMana()) * 100), 6);
            }


        }
    }
}

/*
blit(
int xPos,		// x position relative to the screen image below it (not the entire screen).
int yPos,		// y position relative to the screen image below it (not the entire screen).
int blitOffset,		// z position (blitOffSet)
float textureX,		// x position on the texture image to draw from
float textureY,		// y position on the texture image to draw from
int imgSizeX,		// x image size to display (like crop in PS)
int imgSizeY,		// y image size to display (like crop in PS)
int scaleY,		// y image size (will scale image to fit)
int scalex)		// x image size (will scale image to fit)

 */
