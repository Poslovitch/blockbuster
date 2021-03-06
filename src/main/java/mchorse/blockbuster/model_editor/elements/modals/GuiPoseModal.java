package mchorse.blockbuster.model_editor.elements.modals;

import java.util.ArrayList;
import java.util.List;

import mchorse.blockbuster.model_editor.GuiModelEditor;
import mchorse.blockbuster.model_editor.elements.GuiThreeInput;
import mchorse.blockbuster.model_editor.elements.GuiThreeInput.IMultiInputListener;
import mchorse.blockbuster.model_editor.elements.scrolls.GuiPosesView;
import mchorse.blockbuster.model_editor.modal.GuiModal;
import mchorse.metamorph.api.models.Model;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

/**
 * Poses modal
 *
 * This modal is responsible for adding an ability to manipulate poses.
 */
public class GuiPoseModal extends GuiModal implements IMultiInputListener
{
    private final String strName = I18n.format("blockbuster.gui.me.pose_name");

    private GuiTextField name;
    private GuiButton done;
    private GuiButton add;
    private GuiButton remove;
    private GuiButton select;
    private GuiPosesView poses;
    private GuiThreeInput size;

    public GuiPoseModal(int add_id, int remove_id, int select_id, GuiScreen parent, FontRenderer font)
    {
        super(parent, font);

        this.name = new GuiTextField(0, font, 0, 0, 0, 18);
        this.done = new GuiButton(-1, 0, 0, 0, 20, I18n.format("blockbuster.gui.done"));
        this.add = new GuiButton(add_id, 0, 0, 0, 20, I18n.format("blockbuster.gui.add"));
        this.remove = new GuiButton(remove_id, 0, 0, 0, 20, I18n.format("blockbuster.gui.remove"));
        this.select = new GuiButton(select_id, 0, 0, 0, 20, I18n.format("blockbuster.gui.select"));
        this.size = new GuiThreeInput(-2, font, 0, 0, 0, this);

        this.buttons.add(this.done);
        this.buttons.add(this.add);
        this.buttons.add(this.remove);
        this.buttons.add(this.select);

        this.poses = new GuiPosesView(parent);
        this.updatePoses();

        this.height = 122 + 20;
        this.label = I18n.format("blockbuster.gui.me.pose_title_modal");
    }

    private void updatePoses()
    {
        List<String> limbs = new ArrayList<String>();

        limbs.addAll(((GuiModelEditor) this.parent).data.poses.keySet());
        this.poses.setPoses(limbs);

        this.updateScale();
    }

    private void updateScale()
    {
        Model.Pose pose = ((GuiModelEditor) this.parent).getCurrentLimbPose();

        if (pose != null)
        {
            this.size.a.setText(String.valueOf(pose.size[0]));
            this.size.b.setText(String.valueOf(pose.size[1]));
            this.size.c.setText(String.valueOf(pose.size[2]));
        }
    }

    public String getSelected()
    {
        return this.poses.getSelected();
    }

    public String getName()
    {
        return this.name.getText();
    }

    public void setSelected(String pose)
    {
        this.poses.setSelected(pose);
    }

    @Override
    public void initiate()
    {
        super.initiate();

        int x = this.x;
        int y = this.y;
        int w = this.width;
        int h = this.height;

        this.name.xPosition = x + 5;
        this.name.yPosition = y + h - 22 - 24 + 1;
        this.name.width = w - 62;
        this.add.xPosition = x + w - 4 - 50;
        this.add.yPosition = y + h - 22 - 24;
        this.add.width = 50;

        this.remove.xPosition = this.select.xPosition = this.done.xPosition = x + 4;
        this.remove.width = this.select.width = this.done.width = 60;

        this.remove.yPosition = y + h - 24 * 5 + 2;
        this.select.yPosition = y + h - 24 * 4 + 2;
        this.done.yPosition = y + h - 24 * 3 + 2;

        this.poses.updateRect(x + 68, y + 4, w - 72, h - 32 - 18);
        this.size.update(x + 4, y + h - 22, w - 8);
        this.updateScale();
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        super.actionPerformed(button);

        if (button == this.remove || button == this.add)
        {
            this.updatePoses();
        }

        if (button == this.select)
        {
            this.updateScale();
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode)
    {
        this.name.textboxKeyTyped(typedChar, keyCode);
        this.size.keyTyped(typedChar, keyCode);

        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void setValue(int id, int subset, String value)
    {
        Model.Pose transform = ((GuiModelEditor) this.parent).getCurrentLimbPose();

        if (transform != null)
        {
            try
            {
                transform.size[subset] = Float.parseFloat(value);
            }
            catch (NumberFormatException e)
            {}
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        this.name.mouseClicked(mouseX, mouseY, mouseButton);
        this.poses.mouseClicked(mouseX, mouseY, mouseButton);
        this.size.mouseClicked(mouseX, mouseY, mouseButton);

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void wheelScroll(int mouseX, int mouseY, int scroll)
    {
        this.poses.scrollMouse(scroll, mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        this.poses.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void drawModal(int mouseX, int mouseY, float partialTicks)
    {
        super.drawModal(mouseX, mouseY, partialTicks);

        this.name.drawTextBox();
        this.poses.draw(mouseX, mouseY, partialTicks);
        this.size.draw();

        if (!this.name.isFocused() && this.name.getText().isEmpty())
        {
            this.font.drawStringWithShadow(this.strName, this.name.xPosition + 4, this.name.yPosition + 5, 0xaaaaaa);
        }
    }
}