package icu.patchouli9.tools.mixins.minecraft;

import java.util.HashMap;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import icu.patchouli9.tools.api.EntityLivingBaseInterface;

@Mixin(net.minecraft.entity.EntityLivingBase.class)
public class EntityLivingBaseMixin implements EntityLivingBaseInterface {

    @Shadow
    @Final
    HashMap activePotionsMap;

    @Override
    public HashMap getActivePotionsMap() {
        return this.activePotionsMap;
    }
}
