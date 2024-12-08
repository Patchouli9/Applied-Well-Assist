package icu.patchouli9.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import icu.patchouli9.tools.ModuleManager.Module;
import icu.patchouli9.tools.ModuleManager.ModuleManager;

public class EventHandler {
    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance()
            .bus()
            .register(this);
    }
    // @SubscribeEvent
    // public void Test(TickEvent.PlayerTickEvent event) {
    // Main.LOG.info("1");
    // }

    @SubscribeEvent
    public void PlayerTick(TickEvent.PlayerTickEvent event) throws Exception {
        if (ModuleManager.modules != null) {
            for (Module module : ModuleManager.modules) {
                if (module.enabled) {
                    module.update();
                }
            }
        }
    }

    @SubscribeEvent
    public void RenderTick(TickEvent.RenderTickEvent event) throws Exception {
        if (ModuleManager.modules != null) {
            for (Module module : ModuleManager.modules) {
                if (module.enabled) {
                    module.render();
                }
            }
        }
    }

    @SubscribeEvent
    public void keyInputEvent(InputEvent.KeyInputEvent event) throws Exception {
        if (ModuleManager.modules != null) {
            for (Module module : ModuleManager.modules) {
                if (module.key != -1) {
                    int key = Keyboard.getEventKey();

                    if (module.enabled) {
                        module.keyInput(key);
                    }

                    if (module.keybind.getIsKeyPressed()) {
                        Main.LOG.info(module.key + " is pressed");
                        module.set(!module.enabled);
                        if (Minecraft.getMinecraft().thePlayer != null) {
                            Minecraft.getMinecraft().thePlayer
                                .addChatMessage(new ChatComponentText(module.name + ": " + module.enabled));
                        }
                    }
                }
            }
        }
    }
}
