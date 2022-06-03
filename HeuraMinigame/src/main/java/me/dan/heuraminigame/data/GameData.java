package me.dan.heuraminigame.data;

import lombok.Getter;
import lombok.Setter;
import me.dan.heuraminigame.HeuraMinigame;
import me.dan.pluginapi.file.gson.GsonUtil;
import org.bukkit.Location;

import java.io.File;

@Getter
@Setter
public class GameData {

    @Getter
    private static GameData instance;

    private Location lobbyLocation;
    private Location centre;

    public void save() {
        GsonUtil.save(HeuraMinigame.getInstance().getDataFolder(), "gamedata", this);
    }

    public static void load() {
        File file = new File(HeuraMinigame.getInstance().getDataFolder(), "gamedata.json");
        if (!file.exists()) {
            instance = new GameData();
            return;
        }

        instance = GsonUtil.read(HeuraMinigame.getInstance().getDataFolder(), file.getName(), GameData.class);

    }

    public boolean isSetup() {
        return lobbyLocation != null && centre != null;
    }

}
