package icu.patchouli9.tools.modules;

import icu.patchouli9.tools.Main;
import icu.patchouli9.tools.ModuleManager.Module;
import icu.patchouli9.tools.api.EntityLivingBaseInterface;
import icu.patchouli9.tools.api.PotionEffectInterface;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AntiNegative extends Module {
    public AntiNegative(String name, int key) {
        super(name, key);
    }

    @Override
    public void update(){
        EntityClientPlayerMP player = MC.thePlayer;

        UUID moveSlowdownUUID=Potion.moveSlowdown.func_111186_k().get(SharedMonsterAttributes.movementSpeed).getID();
        IAttributeInstance speedAttr = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        AttributeModifier modifier = speedAttr.getModifier(moveSlowdownUUID);
        if (modifier != null) {
            speedAttr.removeModifier(modifier);
        }

        player.removePotionEffect(Potion.digSlowdown.getId());
    }

}
