package icu.patchouli9.tools.mixins.minecraft;

import icu.patchouli9.tools.api.PotionEffectInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.potion.PotionEffect.class)
public abstract class PotionEffectMixin implements PotionEffectInterface {
    @Shadow
    private int duration;

    @Override
    public void setDuration(int newDuration) {
        this.duration=newDuration;
    }
}
