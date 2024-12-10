package icu.patchouli9.tools.modules;

import net.minecraft.client.entity.EntityClientPlayerMP;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

import icu.patchouli9.tools.ModuleManager.Module;

public class Speed extends Module {
    private static final float BASE_WALK_SPEED = 0.1F; // default: 0.1F
    public Speed(String name, int key) {
        super(name, key);
    }

    @Override
    public void disable() {
        EntityClientPlayerMP player = MC.thePlayer;
        IAttributeInstance movementSpeed = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        movementSpeed.setBaseValue(0.1F);
    }

    @Override
    public void update() {
        EntityClientPlayerMP player = MC.thePlayer;
        IAttributeInstance movementSpeed = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        movementSpeed.setBaseValue(0.2F);
    }
}
