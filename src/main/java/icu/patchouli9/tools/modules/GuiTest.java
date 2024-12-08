package icu.patchouli9.tools.modules;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import icu.patchouli9.tools.Main;
import icu.patchouli9.tools.ModuleManager.GuiHandler;
import icu.patchouli9.tools.ModuleManager.Module;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GuiTest extends Module {
    public int GuiID;
    public GuiTest(String name, int key) {
        super(name, key);
    }
    @Override
    public void init(){
        GuiID=GuiHandler.instance.guiRegister(GuiSimple.class);
    }
    @Override
    public void enable() throws Exception {
        EntityClientPlayerMP player = MC.thePlayer;
        player.openGui(Main.instance, GuiID, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
        enabled = false;
    }
}
