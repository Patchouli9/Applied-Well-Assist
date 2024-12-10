package icu.patchouli9.tools.modules;

import icu.patchouli9.tools.annotations.RegisterSettingEntry;
import icu.patchouli9.tools.annotations.settingTuple;
import icu.patchouli9.tools.types.EntryType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;

import icu.patchouli9.tools.ModuleManager.Module;

@RegisterSettingEntry(name = "Fly",
    settings=[
    @settingTuple(type = EntryType.TOGGLE, description = "Toggle", varName = "enabled",defaultToggle = false),
    @settingTuple(type = EntryType.NUMBER, description = "Toggle", varName = "enabled",defaultValue = 2),
    ]
)
public class Fly extends Module {
    public Fly(String name, int key) {
        super(name, key);
    }

    @Override
    public void update() {
        EntityClientPlayerMP player = MC.thePlayer;
        GameSettings gameSettings = MC.gameSettings;
        if (player == null) return;

        player.motionX = 0;
        player.motionY = 0;
        player.motionZ = 0;

        float multiplier = 1;
        if (gameSettings.keyBindSprint.getIsKeyPressed()) multiplier = 3;

        float strafe = 0.0F;
        float forward = 0.0F;
        float upward = 0.0F;

        if (gameSettings.keyBindForward.getIsKeyPressed()) ++forward;
        if (gameSettings.keyBindBack.getIsKeyPressed()) --forward;
        if (gameSettings.keyBindLeft.getIsKeyPressed()) ++strafe;
        if (gameSettings.keyBindRight.getIsKeyPressed()) --strafe;
        if (gameSettings.keyBindJump.getIsKeyPressed()) ++upward;
        if (gameSettings.keyBindSneak.getIsKeyPressed()) --upward;

        // player.motionX = moveForward;
        // player.motionZ = moveStrafe;

        player.motionY = upward * multiplier;

        float f3 = strafe * strafe + forward * forward;

        if (f3 == 0) {
            return;
        }

        f3 = MathHelper.sqrt_float(f3);
        f3 = 1 / f3;
        strafe *= f3;
        forward *= f3;
        float f4 = MathHelper.sin(player.rotationYaw * (float) Math.PI / 180.0F);
        float f5 = MathHelper.cos(player.rotationYaw * (float) Math.PI / 180.0F);
        player.motionX = (strafe * f5 - forward * f4) * multiplier;
        player.motionZ = (forward * f5 + strafe * f4) * multiplier;
        // modified from net.minecraft.entity.Entity.moveFlying

        //
        // vehicle.moveEntity(0,0.3,0);

        // if(Keyboard.isKeyDown(W.getKeyCode())){
        //
        // }
    }

}
