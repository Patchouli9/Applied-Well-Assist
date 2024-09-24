package icu.patchouli9.tools.modules;

import icu.patchouli9.tools.ModuleManager.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.storage.IPlayerFileData;
import org.lwjgl.input.Keyboard;

import java.awt.Color;


public class XYZ extends Module {
    public XYZ(String name, int key) {
        super(name, key);
    }

    @Override
    public void render() {
        Minecraft MC = Minecraft.getMinecraft();
        EntityClientPlayerMP player = MC.thePlayer;
        if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && player != null) {
            Vec3 pos = player.getPosition(1.0F);
            MC.fontRenderer.drawStringWithShadow(
                "XYZ: " + Math.round(pos.xCoord * 1000.0D) / 1000.0D + ", " +
                    Math.round(pos.yCoord * 1000.0D) / 1000.0D + ", " +
                    Math.round(pos.zCoord * 1000.0D) / 1000.0D,
                0, 2, Color.WHITE.getRGB()
            );

            MC.fontRenderer.drawStringWithShadow(
                "MotionXYZ: " + Math.round(player.motionX * 1000.0D) / 1000.0D + ", " +
                    Math.round(player.motionY * 1000.0D) / 1000.0D + ", " +
                    Math.round(player.motionZ * 1000.0D) / 1000.0D,
                0, 10, Color.WHITE.getRGB()
            );
        }
    }
}
