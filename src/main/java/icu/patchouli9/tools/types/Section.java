package icu.patchouli9.tools.types;

import icu.patchouli9.tools.ModuleManager.Module;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Section {
    public String name = "Section";
    public Module module;
    public List<Setting> settings = new ArrayList<>();

    public static class Setting {
        public String label;
        public EntryType type;
        public Field field;
        public Class<?> clazz;
        public Setting(String label, EntryType type, Field field) {
            this.label = label;
            this.type = type;
            this.field = field;
        }
    }
}
