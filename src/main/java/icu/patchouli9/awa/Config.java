package icu.patchouli9.awa;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Config {

    public static String greeting = "Hello World";
    public static File file = new File("config/tools.json");
    public static JsonObject json;

    public Config() {}

    public static void init() {
        Main.LOG.info("Config File at " + file.toPath());
        if (!file.exists()) {
            initFile();
        }
        readFile();
    }

    public static void initFile() {
        try {
            Files.write(file.toPath(), "{}".getBytes());
        } catch (IOException e) {
            Main.LOG.error("Initialize Config failed.", new RuntimeException(e));
        }
    }

    public static void readFile() {
        try {
            FileReader reader = new FileReader(
                file.toPath()
                    .toString());
            json = new JsonParser().parse(reader)
                .getAsJsonObject();
        } catch (IOException | JsonSyntaxException | IllegalStateException e) {
            Main.LOG.warn("Failed to load config, init it.", new RuntimeException(e));
            initFile();
            readFile();
        }
    }

    public static void writeFile() {
        try {
            Files.write(
                file.toPath(),
                json.toString()
                    .getBytes());
            Main.LOG.info(json);
            Main.LOG.info(file.toPath());
        } catch (IOException e) {
            Main.LOG.warn("Failed to save config, init it.", new RuntimeException(e));
            initFile();
        }
    }
}
