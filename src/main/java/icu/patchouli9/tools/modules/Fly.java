package icu.patchouli9.tools.modules;

import icu.patchouli9.tools.ModuleManager.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

import java.security.Key;

public class Fly extends Module {
    public Fly(String name, int key) {
        super(name, key);
    }

    @Override
    public void update() {
        Minecraft MC = Minecraft.getMinecraft();
        EntityClientPlayerMP player = MC.thePlayer;
        GameSettings gameSettings = MC.gameSettings;
        if (player == null) return;

        player.motionX = 0;
        player.motionY = 0;
        player.motionZ = 0;

        float strafe = 0.0F;
        float forward = 0.0F;
        float upward = 0.0F;

        if (gameSettings.keyBindForward.getIsKeyPressed()) ++forward;
        if (gameSettings.keyBindBack.getIsKeyPressed()) --forward;
        if (gameSettings.keyBindLeft.getIsKeyPressed()) ++strafe;
        if (gameSettings.keyBindRight.getIsKeyPressed()) --strafe;
        if (gameSettings.keyBindJump.getIsKeyPressed()) ++upward;
        if (gameSettings.keyBindSneak.getIsKeyPressed()) --upward;

//        player.motionX = moveForward;
//        player.motionZ = moveStrafe;
        player.motionY = upward;

        float f3 = strafe * strafe + forward * forward;
        f3 = MathHelper.sqrt_float(f3);
        f3 = 1 / f3;
        strafe *= f3;
        forward *= f3;
        float f4 = MathHelper.sin(player.rotationYaw * (float) Math.PI / 180.0F);
        float f5 = MathHelper.cos(player.rotationYaw * (float) Math.PI / 180.0F);
        player.motionX = strafe * f5 - forward * f4;
        player.motionZ = forward * f5 + strafe * f4;
        // modified from net.minecraft.entity.Entity.moveFlying

//
//        vehicle.moveEntity(0,0.3,0);

//        if(Keyboard.isKeyDown(W.getKeyCode())){
//
//        }
    }

}
