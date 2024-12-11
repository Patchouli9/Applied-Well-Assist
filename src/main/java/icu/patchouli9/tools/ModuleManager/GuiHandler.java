package icu.patchouli9.tools.ModuleManager;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    List<Class<? extends GuiScreen>> GUIs = new ArrayList<>();
    public final static GuiHandler instance = new GuiHandler();

    public int guiRegister(Class<? extends GuiScreen> gui) {
        GUIs.add(gui);
        return GUIs.size() - 1;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        GuiScreen tmp = null;
        try {
            tmp = GUIs.get(ID)
                .getDeclaredConstructor()
                .newInstance();
        } catch (Exception ignored) {}
        return tmp;
    }
}
