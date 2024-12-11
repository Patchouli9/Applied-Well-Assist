package icu.patchouli9.tools.modules;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import icu.patchouli9.tools.ModuleManager.Module;

public class InventoryMove extends Module {

    public InventoryMove(String name, int key) {
        super(name, key);
    }

    private boolean wasInventoryOpen = false;

    @Override
    public void update() {
        GuiScreen currentScreen = MC.currentScreen;
        boolean isInventoryOpen = currentScreen instanceof GuiInventory;

        if (wasInventoryOpen && !isInventoryOpen) {
            restoreMovementKeys();
        }
        wasInventoryOpen = isInventoryOpen;
    }

    private void restoreMovementKeys() {
        KeyBinding[] movementKeys = new KeyBinding[] { MC.gameSettings.keyBindForward, MC.gameSettings.keyBindLeft,
            MC.gameSettings.keyBindBack, MC.gameSettings.keyBindRight, MC.gameSettings.keyBindSneak,
            MC.gameSettings.keyBindSprint };

        for (KeyBinding keyBinding : movementKeys) {
            if (Keyboard.isKeyDown(keyBinding.getKeyCode())) {
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), true);
            }
        }
    }
}
