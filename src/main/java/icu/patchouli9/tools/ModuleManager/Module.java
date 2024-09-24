package icu.patchouli9.tools.ModuleManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import icu.patchouli9.tools.Config;
import icu.patchouli9.tools.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Module {
    public final String name;
    public final int key;
    public boolean enabled = false;

    public Module(String name, int key){
        this.name = name;
        this.key = key;
    }

    public void disabled() throws Exception{
    }

    public void update() throws Exception{
    }

    public void render() throws Exception{
    }

    public void keyInput(int key) throws Exception{

    }

    public void enabled() throws Exception{
    }

    public void set(boolean enabled) throws Exception {
        this.enabled = enabled;

        if(enabled){
            enabled();
            Config.json.addProperty(this.name,true);
        }
        else{
            disabled();
            try {
                Config.json.addProperty(this.name,false);
            }
            catch (Exception e){
                Main.LOG.error("Failed to remove the module enabled in the config file: "+this.name);
            }
        }
        Config.writeFile();
    }



}
