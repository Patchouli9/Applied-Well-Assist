package icu.patchouli9.awa.modules;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;

import icu.patchouli9.awa.ModuleManager.Module;
import icu.patchouli9.awa.annotations.RegisterSettingEntry;
import icu.patchouli9.awa.annotations.settingTuple;
import icu.patchouli9.awa.types.EntryType;

@RegisterSettingEntry(
    name = "不再牢大",
    settings = { @settingTuple(type = EntryType.TOGGLE, description = "启用", varName = "enabled") })
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
