package icu.patchouli9.tools.mixins.minecraft;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = net.minecraft.entity.player.PlayerCapabilities.class)
public class PlayerCapabilitiesMixin {

    // @Inject(method = "onItemRightClick",
    // at = @At("HEAD"),
    // require = 1,
    // locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    // private void onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer,
    // CallbackInfoReturnable<ItemStack> callbackInfoReturnable) {
    // Main.info("You are reading a book");
    // }
}
