package icu.patchouli9.awa.modules;

import java.awt.Color;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.Vec3;

import org.lwjgl.input.Keyboard;

import icu.patchouli9.awa.ModuleManager.Module;

public class XYZ extends Module {

    public XYZ(String name, int key) {
        super(name, key);
    }

    @Override
    public void render() {
        EntityClientPlayerMP player = MC.thePlayer;
        if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && player != null) {
            Vec3 pos = player.getPosition(1.0F);
            MC.fontRenderer.drawStringWithShadow(
                "XYZ: " + Math.round(pos.xCoord * 1000.0D) / 1000.0D
                    + ", "
                    + Math.round(pos.yCoord * 1000.0D) / 1000.0D
                    + ", "
                    + Math.round(pos.zCoord * 1000.0D) / 1000.0D,
                0,
                2,
                Color.WHITE.getRGB());

            MC.fontRenderer.drawStringWithShadow(
                "MotionXYZ: " + Math.round(player.motionX * 1000.0D) / 1000.0D
                    + ", "
                    + Math.round(player.motionY * 1000.0D) / 1000.0D
                    + ", "
                    + Math.round(player.motionZ * 1000.0D) / 1000.0D,
                0,
                10,
                Color.WHITE.getRGB());
        }
    }
}
