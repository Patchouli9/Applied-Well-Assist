package icu.patchouli9.tools.modules;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.AxisAlignedBB;

import icu.patchouli9.tools.ModuleManager.Module;

public class AutoHit extends Module {

    public AutoHit(String name, int key) {
        super(name, key);
    }

    @Override
    public void update() {
        Minecraft MC = Minecraft.getMinecraft();
        EntityClientPlayerMP player = MC.thePlayer;
        if (player == null) return;
        int scanRadius = 8;
        AxisAlignedBB scanArea = player.boundingBox.expand(scanRadius, scanRadius, scanRadius);

        List<Entity> entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, scanArea);
        // Main.LOG.info("entities: "+entities);
        for (Entity entity : entities) {
            attackEntity(player, entity);
        }
    }

    private static boolean isValidTarget(EntityPlayer player, Entity entity) {
        if (entity == player) return false;
        if (entity instanceof EntityLivingBase) {
            return !entity.isDead && entity.isEntityAlive();
        }
        return false;
    }

    private static void attackEntity(EntityPlayer player, Entity targetEntity) {
        if (!isValidTarget(player, targetEntity)) return;
        C02PacketUseEntity attackPacket = new C02PacketUseEntity(targetEntity, C02PacketUseEntity.Action.ATTACK);
        Minecraft MC = Minecraft.getMinecraft();
        if (MC.thePlayer != null) {
            NetHandlerPlayClient connection = MC.getNetHandler();
            if (connection != null) {
                connection.addToSendQueue(attackPacket);
            }
        }
    }
}
