package icu.patchouli9.tools.modules;

import icu.patchouli9.tools.Main;
import icu.patchouli9.tools.ModuleManager.GuiHandler;
import icu.patchouli9.tools.ModuleManager.Module;
import icu.patchouli9.tools.gui.GuiSettings;
import icu.patchouli9.tools.gui.GuiSimple;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class GuiTest extends Module {
    public int GuiID;
    public GuiTest(String name, int key) {
        super(name, key);
    }
    @Override
    public void init(){
        GuiID=GuiHandler.instance.guiRegister(GuiSettings.class);
    }
    @Override
    public void enable() throws Exception {
        EntityClientPlayerMP player = MC.thePlayer;
        player.openGui(Main.instance, GuiID, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
        enabled = false;
    }
}
