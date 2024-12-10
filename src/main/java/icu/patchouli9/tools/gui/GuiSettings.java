package icu.patchouli9.tools.gui;

import icu.patchouli9.tools.Main;
import icu.patchouli9.tools.ModuleManager.ModuleManager;
import icu.patchouli9.tools.modules.Fly;
import icu.patchouli9.tools.types.EntryType;
import icu.patchouli9.tools.types.Section;
import icu.patchouli9.tools.types.Section.Setting;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static icu.patchouli9.tools.gui.SettingsRegistry.SECTIONS;

public class GuiSettings extends GuiScreen {
    private int leftColumnX;
    private int entryHeight = 20;
    private int titleHeight = 30;
    private int topMargin = 40;
    private int columnGap = 20;

    private List<GuiTextField> textFields = new ArrayList<>();
    private List<GuiButton> toggleButtons = new ArrayList<>();

    // 滚动相关
    private int scrollOffset = 0;
    private int maxVisibleHeight; // 可见区域高度
    private int totalHeight; // 所有条目的总高度
    private int mouseX, mouseY;
    private List<Setting> buttonIndex2settings = new ArrayList<>();
    private List<Setting> textIndex2settings = new ArrayList<>();

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);

        int center = this.width / 2;
        leftColumnX = center - 100;
        int rightColumnX = center + 20;

        this.buttonList.clear();
        this.textFields.clear();
        this.toggleButtons.clear();


        // totalHeight = SETTINGS.size() * entryHeight;
        maxVisibleHeight = this.height - topMargin - 50; // 50 for finish-button

        int y = topMargin;
        int textFieldIndex = 0;
        int toggleIndex = 0;
        for (Section section : SECTIONS) {
            y += titleHeight;
            for (int i = 0; i < section.settings.size(); i++) {
                Setting setting = section.settings.get(i);
                switch (setting.type) {
                    case NUMBER:
                        GuiTextField textField = new GuiTextField(fontRendererObj, rightColumnX, y - 5, 60, 20);
                        textField.setText(setting.defaultValue.toString());
                        textFields.add(textField);

                        textIndex2settings.add(setting);
                        break;
                    case TOGGLE:
                        boolean current = (Boolean) setting.defaultValue;
                        GuiButton toggleButton = new GuiButton(100 + i, rightColumnX, y - 5, 60, 20, current ? "开启" : "关闭");
                        this.buttonList.add(toggleButton);
                        toggleButtons.add(toggleButton);

                        buttonIndex2settings.add(setting);
                        break;
                }
                y += entryHeight;
            }

        }
        totalHeight = y;
        this.buttonList.add(new GuiButton(9999, this.width / 2 - 40, this.height - 30, 80, 20, "完成"));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        for (GuiTextField field : textFields) {
            field.textboxKeyTyped(typedChar, keyCode);
        }
        if (keyCode == Keyboard.KEY_ESCAPE) {
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        // 考虑滚动偏移，要检测实际点击的是哪个控件位置
        int yStart = topMargin - scrollOffset;
        for (int i = 0; i < textFields.size(); i++) {
            GuiTextField field = textFields.get(i);
            field.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int scroll = Mouse.getDWheel();
        if (scroll != 0) {
            scrollOffset = scrollOffset - scroll / 5;
            scrollOffset = MathHelper.clamp_int(scrollOffset, 0, Math.max(0, totalHeight - maxVisibleHeight));
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 9999) {
            saveSettings();
            this.mc.displayGuiScreen(null);
            return;
        }

        int index = button.id - 100;
        if (index >= 0 && index < buttonIndex2settings.size()) {
            Setting setting = buttonIndex2settings.get(index);
            if (setting.type == EntryType.TOGGLE) {
                boolean current = (Boolean) setting.defaultValue;
                current = !current;
                setting.defaultValue = current;
                button.displayString = current ? "开启" : "关闭";
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.drawDefaultBackground();


        String title = "设置";
        drawCenteredString(fontRendererObj, title, width / 2, 15, 0xFFFFFF);

        int y = topMargin - scrollOffset;

        int textFieldCount = 0;
        int toggleCount = 0;


        for (Section section : SECTIONS) {
            if (y + titleHeight >= topMargin && y <= topMargin + maxVisibleHeight) {
                drawCenteredString(fontRendererObj, section.name, width / 2, y, 0xFFFFFF);
            }
            y += titleHeight;
            for (int i = 0; i < section.settings.size(); i++) {
                Setting setting = section.settings.get(i);
                if (y + entryHeight >= topMargin && y <= topMargin + maxVisibleHeight) {
                    drawString(fontRendererObj, setting.label + ":", leftColumnX, y, 0xFFFFFF);
                    switch (setting.type) {
                        case NUMBER:
                            GuiTextField field = textFields.get(textFieldCount);
                            field.yPosition = y - 5;
                            field.drawTextBox();
                            textFieldCount++;
                            break;
                        case TOGGLE:
                            GuiButton toggle = toggleButtons.get(toggleCount);
                            toggle.yPosition = y - 5;
                            toggle.drawButton(mc, mouseX, mouseY);
                            toggleCount++;
                            break;
                    }
                } else {
                    switch (setting.type) {
                        case NUMBER:
                            GuiTextField field = textFields.get(textFieldCount++);
                            field.yPosition = y - 5;
                            break;
                        case TOGGLE:
                            GuiButton toggle = toggleButtons.get(toggleCount++);
                            toggle.yPosition = y - 5;
                            break;
                    }
                }
                y += entryHeight;
            }

        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        for (GuiTextField field : textFields) {
            field.updateCursorCounter();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    private void saveSettings() {
        int textFieldIndex = 0;
        int toggleIndex = 0;

        for (Section section : SECTIONS) {
            for (Setting setting: section.settings) {
                try {
                    switch (setting.type) {
                        case NUMBER:
                            String textVal = textFields.get(textFieldIndex++).getText();
                            setting.field.set(section.module, Double.parseDouble(textVal));
                            break;
                        case TOGGLE:
                            boolean boolVal = (Boolean) setting.defaultValue;
                            setting.field.set(section.module, boolVal);
                            break;
                        }
                } catch (Exception e) {
                    Main.info(Arrays.toString(e.getStackTrace()));
                }
            }
        }
    }
}
