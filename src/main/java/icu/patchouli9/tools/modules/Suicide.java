package icu.patchouli9.tools.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;

import org.lwjgl.input.Keyboard;

import icu.patchouli9.tools.ModuleManager.Module;

public class Suicide extends Module {

    public Suicide(String name, int key) {
        super(name, key);
    }

    @Override
    public void update() {
        Minecraft MC = Minecraft.getMinecraft();
        if (MC.thePlayer != null) {
            if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
                NetHandlerPlayClient connection = MC.getNetHandler();
                if (connection != null) {
                    MC.thePlayer.fallDistance = 100;
                    connection.addToSendQueue(new C03PacketPlayer(false));
                }
            }
        }
    }
}
