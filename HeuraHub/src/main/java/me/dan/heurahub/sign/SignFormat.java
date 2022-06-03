package me.dan.heurahub.sign;

import me.dan.pluginapi.configuration.Serializable;
import me.dan.pluginapi.file.YamlFile;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignFormat extends Serializable {

    private final String[] lines;

    public SignFormat(String... lines) {
        this.lines = lines;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            if (lines[i] == null) {
                map.put(i + "", " ");
            } else {
                map.put(i + "", lines[i]);
            }
        }
        return map;
    }

    public static SignFormat deserialize(YamlFile yamlFile, String path) {
        YamlConfiguration c = yamlFile.getConfig();
        String[] lines = new String[4];
        for (String key : c.getKeys(false)) {
            int keyInt;
            try {
                keyInt = Integer.parseInt(key);
            } catch (NumberFormatException e) {
                continue;
            }
            if (keyInt < 1 || keyInt > 4) {
                continue;
            }
            lines[keyInt - 1] = c.getString(path + "." + key);
        }
        return new SignFormat(lines);
    }

    public List<String> getLines() {
        List<String> lineList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (lines[i] == null) {
                lineList.add(" ");
                continue;
            }
            lineList.add(lines[i]);
        }
        return lineList;
    }
}
