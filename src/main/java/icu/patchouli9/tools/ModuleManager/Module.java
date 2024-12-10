package icu.patchouli9.tools.ModuleManager;

import icu.patchouli9.tools.Config;
import icu.patchouli9.tools.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class Module {
    public final String name;
    public final int key;
    public final KeyBinding keybind;
    public boolean enabled = false;
    protected final Minecraft MC = Minecraft.getMinecraft();
    public Module(String name, int key) {
        this.name = name;
        this.key = key;
        this.keybind=new KeyBinding(name, key, "Patchouli9's Tools");
    }

    public void disable() throws Exception {}

    public void init() throws Exception {}

    public void update() throws Exception {}

    public void render() throws Exception {}

    public void keyInput(int key) throws Exception {

    }

    public void enable() throws Exception {}

    public void set(boolean enabled) throws Exception {
        this.enabled = enabled;
        if (enabled) {
            enable();
            Config.json.addProperty(this.name, true);
        } else {
            disable();
            try {
                Config.json.addProperty(this.name, false);
            } catch (Exception e) {
                Main.LOG.error("Failed to remove the module enabled in the config file: " + this.name);
            }
        }
        Config.writeFile();
    }

}
