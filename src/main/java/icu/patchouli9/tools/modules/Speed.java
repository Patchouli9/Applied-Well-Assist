package icu.patchouli9.tools.modules;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

import icu.patchouli9.tools.ModuleManager.Module;
import icu.patchouli9.tools.annotations.RegisterSettingEntry;
import icu.patchouli9.tools.annotations.settingTuple;
import icu.patchouli9.tools.types.EntryType;

@RegisterSettingEntry(
    name = "加速加速",
    settings = { @settingTuple(type = EntryType.TOGGLE, description = "启用", varName = "enabled"),
        @settingTuple(type = EntryType.TEXT, description = "速度倍率", varName = "SpeedMultiplier") })
public class Speed extends Module {

    private static final float BASE_WALK_SPEED = 0.1F; // default: 0.1F
    public float SpeedMultiplier = 2;

    public Speed(String name, int key) {
        super(name, key);
    }

    @Override
    public void disable() {
        EntityClientPlayerMP player = MC.thePlayer;
        IAttributeInstance movementSpeed = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        movementSpeed.setBaseValue(BASE_WALK_SPEED);
    }

    @Override
    public void update() {
        EntityClientPlayerMP player = MC.thePlayer;
        IAttributeInstance movementSpeed = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        movementSpeed.setBaseValue(BASE_WALK_SPEED * SpeedMultiplier);
    }
}
