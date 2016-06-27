package noname.blockbuster.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import noname.blockbuster.Blockbuster;

/**
 * Recording GUI overlay
 *
 * This class is responsible for rendering red circle (like the icon that
 * represents recording in progress) and name of the recording file
 */
public class GuiRecordingOverlay extends Gui
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(Blockbuster.MODID, "textures/gui/recording.png");

    protected Minecraft mc;
    protected String caption;
    protected boolean isVisible = false;

    public GuiRecordingOverlay(Minecraft mc)
    {
        this.mc = mc;
    }

    public void setCaption(String caption)
    {
        this.caption = I18n.format("blockbuster.recording", caption);
    }

    public void setVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    public void draw(int width, int height)
    {
        if (!this.isVisible)
            return;

        FontRenderer font = this.mc.fontRendererObj;

        this.mc.renderEngine.bindTexture(TEXTURE);

        GlStateManager.pushAttrib();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();

        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        this.drawTexturedModalRect(4, 4, 0, 0, 16, 16);
        font.drawStringWithShadow(this.caption, 22, 8, 0xffffffff);

        GlStateManager.popAttrib();
    }
}
