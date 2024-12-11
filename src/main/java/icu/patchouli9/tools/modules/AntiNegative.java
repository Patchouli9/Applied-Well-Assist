package icu.patchouli9.tools.modules;

import java.util.UUID;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;

import icu.patchouli9.tools.ModuleManager.Module;
import icu.patchouli9.tools.annotations.RegisterSettingEntry;
import icu.patchouli9.tools.annotations.settingTuple;
import icu.patchouli9.tools.types.EntryType;

@RegisterSettingEntry(
    name = "我就感觉到快",
    settings = { @settingTuple(type = EntryType.TOGGLE, description = "启用", varName = "enabled") })
public class AntiNegative extends Module {

    public AntiNegative(String name, int key) {
        super(name, key);
    }

    @Override
    public void update() {
        EntityClientPlayerMP player = MC.thePlayer;

        UUID moveSlowdownUUID = Potion.moveSlowdown.func_111186_k()
            .get(SharedMonsterAttributes.movementSpeed)
            .getID();
        IAttributeInstance speedAttr = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        AttributeModifier modifier = speedAttr.getModifier(moveSlowdownUUID);
        if (modifier != null) {
            speedAttr.removeModifier(modifier);
        }

        player.removePotionEffect(Potion.digSlowdown.getId());
    }

}
