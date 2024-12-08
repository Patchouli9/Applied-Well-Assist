package icu.patchouli9.tools.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;

import icu.patchouli9.tools.ModuleManager.Module;

public class NoFall extends Module {
    public NoFall(String name, int key) {
        super(name, key);
    }

    @Override
    public void update() {
        if (MC.thePlayer != null && MC.thePlayer.fallDistance >= 3) {
            NetHandlerPlayClient connection = MC.getNetHandler();
            if (connection != null) {
                connection.addToSendQueue(new C03PacketPlayer(true));
            }
        }
    }
}
