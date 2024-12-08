package icu.patchouli9.tools.modules;

// GuiSimple.java
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;

public class GuiSimple extends GuiScreen {
    private GuiTextField textField;
    private GuiButton button;

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        // 添加一个提交按钮
        this.buttonList.add(button = new GuiButton(0, this.width / 2 - 100, this.height / 2 + 20, "提交"));
        // 添加一个输入框
        this.textField = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 20, 200, 20);
        this.textField.setMaxStringLength(50);
        this.textField.setFocused(true);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            String input = this.textField.getText();
            // 处理输入内容
            this.mc.thePlayer.addChatMessage(new ChatComponentText("你输入了: " + input));
            this.mc.displayGuiScreen(null); // 关闭 GUI
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (this.textField.textboxKeyTyped(typedChar, keyCode)) {
            return;
        }
        if (keyCode == Keyboard.KEY_ESCAPE) {
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.textField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.textField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
