package icu.patchouli9.tools.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;

import icu.patchouli9.tools.ModuleManager.Module;

public class Speed extends Module {
    public Speed(String name, int key) {
        super(name, key);
    }

    private static final float BASE_WALK_SPEED = 0.4F; // default: 0.1F
    private static final float SPRINT_WALK_SPEED = 0.6F; // default: 0.15F


    @Override
    public void disable() {
        Minecraft MC = Minecraft.getMinecraft();
        EntityClientPlayerMP player = MC.thePlayer;
        if (player == null) return;
    }

    @Override
    public void update() {
        EntityClientPlayerMP player = MC.thePlayer;
        setPlayerWalkSpeed(player);
//        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 1, true));
//        player.removePotionEffectClient(Potion.digSlowdown.id);
//        player.removePotionEffectClient(Potion.moveSlowdown.id);
//        player.removePotionEffectClient(Potion.blindness.id);
//        player.removePotionEffectClient(Potion.confusion.id);
//        if (Keyboard.isKeyDown(Sprint.getKeyCode())) {
//            player.motionX *= 5;
//            player.motionY *= 5;
//            player.motionZ *= 5;
//        }
        // player.motionX*=5;
        // player.motionY*=5;
        // player.motionZ*=5;
    }
    private void setPlayerWalkSpeed(EntityPlayer player) {
        if (player != null) {
            if (player.isSprinting()) {
                player.capabilities.setPlayerWalkSpeed(SPRINT_WALK_SPEED);
            } else {
                player.capabilities.setPlayerWalkSpeed(BASE_WALK_SPEED);
            }
        }
    }
}
/*
* if (!player.capabilities.isCreativeMode) {
        player.capabilities.setPlayerWalkSpeed(0.15F); // 默认是 0.1F, 这里设置为更高的速度
    }
* */
