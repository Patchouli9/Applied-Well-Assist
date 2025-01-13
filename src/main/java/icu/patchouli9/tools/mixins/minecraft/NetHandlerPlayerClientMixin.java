package icu.patchouli9.tools.mixins.minecraft;

import java.util.Iterator;
import java.util.Objects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import icu.patchouli9.tools.ModuleManager.ModuleManager;

@Mixin(value = net.minecraft.client.network.NetHandlerPlayClient.class)
public class NetHandlerPlayerClientMixin {

    @Shadow
    WorldClient clientWorldController;
    @Shadow
    Minecraft gameController;

    /**
     * @author Patchouli9
     * @reason AntiNegative
     */
    @Overwrite
    public void handleEntityEffect(S1DPacketEntityEffect packetIn) {
        int entityId = packetIn.func_149426_d();
        Entity entity = this.clientWorldController.getEntityByID(entityId);

        if (entity instanceof EntityLivingBase) {
            PotionEffect potioneffect = new PotionEffect(
                packetIn.func_149427_e(),
                packetIn.func_149425_g(),
                packetIn.func_149428_f());
            potioneffect.setPotionDurationMax(packetIn.func_149429_c());
            if (ModuleManager.modulesClass.AntiNegative.enabled
                && entityId == this.gameController.thePlayer.getEntityId()
                && potioneffect.getPotionID() == Potion.moveSlowdown.id) {
                return;
            }
            ((EntityLivingBase) entity).addPotionEffect(potioneffect);
        }
    }

    /**
     * @author Patchouli9
     * @reason AntiNegative
     */
    @Overwrite
    public void handleEntityProperties(S20PacketEntityProperties packetIn) {
        Entity entity = this.clientWorldController.getEntityByID(packetIn.func_149442_c());

        if (entity != null) {
            if (!(entity instanceof EntityLivingBase)) {
                throw new IllegalStateException(
                    "Server tried to update attributes of a non-living entity (actually: " + entity + ")");
            } else {
                BaseAttributeMap baseattributemap = ((EntityLivingBase) entity).getAttributeMap();
                Iterator iterator = packetIn.func_149441_d()
                    .iterator();

                while (iterator.hasNext()) {
                    S20PacketEntityProperties.Snapshot snapshot = (S20PacketEntityProperties.Snapshot) iterator.next();
                    IAttributeInstance iattributeinstance = baseattributemap
                        .getAttributeInstanceByName(snapshot.func_151409_a());

                    boolean isMovementSpeed = Objects.equals(
                        snapshot.func_151409_a(),
                        SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName());

                    if (iattributeinstance == null) {
                        iattributeinstance = baseattributemap.registerAttribute(
                            new RangedAttribute(
                                snapshot.func_151409_a(),
                                0.0D,
                                2.2250738585072014E-308D,
                                Double.MAX_VALUE));
                    }

                    iattributeinstance.setBaseValue(snapshot.func_151410_b());
                    iattributeinstance.removeAllModifiers();
                    Iterator iterator1 = snapshot.func_151408_c()
                        .iterator();

                    while (iterator1.hasNext()) {
                        AttributeModifier attributemodifier = (AttributeModifier) iterator1.next();
                        if (ModuleManager.modulesClass.AntiNegative.enabled && isMovementSpeed
                            && attributemodifier.getAmount() < 0
                            && entity.getEntityId() == this.gameController.thePlayer.getEntityId()) {
                            continue;
                        }
                        iattributeinstance.applyModifier(attributemodifier);
                    }
                }
            }
        }
    }
}
