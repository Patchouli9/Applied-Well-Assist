package icu.patchouli9.tools.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import icu.patchouli9.tools.ModuleManager.Module;

public class Speed extends Module {

    public Speed(String name, int key) {
        super(name, key);
    }

    @Override
    public void update() {
        Minecraft MC = Minecraft.getMinecraft();
        EntityClientPlayerMP player = MC.thePlayer;
        if (player == null) return;
        KeyBinding Sprint = MC.gameSettings.keyBindSprint;
        if (Keyboard.isKeyDown(Sprint.getKeyCode())) {
            player.motionX *= 5;
            player.motionY *= 5;
            player.motionZ *= 5;
        }
        // player.motionX*=5;
        // player.motionY*=5;
        // player.motionZ*=5;
    }
}
