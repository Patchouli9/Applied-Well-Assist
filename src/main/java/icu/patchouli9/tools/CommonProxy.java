package icu.patchouli9.tools;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import icu.patchouli9.tools.ModuleManager.Module;
import icu.patchouli9.tools.ModuleManager.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class CommonProxy {
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        ModuleManager.preinit();

        //Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        Main.LOG.info(Config.greeting);
        Main.LOG.info("I am a Mod at version " + Tags.VERSION);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        ModuleManager.init();

        KeyBinding[] currentBindings = Minecraft.getMinecraft().gameSettings.keyBindings;

        int module_size = 0;
        if (ModuleManager.modules != null) {
            module_size = ModuleManager.modules.size();
        }
        KeyBinding[] newBindings = new KeyBinding[currentBindings.length + module_size];
        System.arraycopy(currentBindings, 0, newBindings, 0, currentBindings.length);
        int tp = currentBindings.length;
        for (Module module : ModuleManager.modules) {
            KeyBinding newBinding = new KeyBinding(module.name, module.key, "Patchouli9's Tools");
            newBindings[tp] = newBinding;
            tp++;
        }
        Minecraft.getMinecraft().gameSettings.keyBindings = newBindings;
        Main.LOG.info("Patchouli9's Tools mod is done.");
        new EventHandler().init();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {

    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
    }
}
