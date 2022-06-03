package me.dan.heuraminigame;

import lombok.Getter;
import me.dan.heuraminigame.configuration.Config;
import me.dan.heuraminigame.configuration.Messages;
import me.dan.heuraminigame.data.GameData;
import me.dan.heuraminigame.game.Game;
import me.dan.heuraminigame.game.MinigameMode;
import me.dan.heuraminigame.listener.*;
import me.dan.pluginapi.configuration.Configuration;
import me.dan.pluginapi.file.YamlFile;
import me.dan.pluginapi.plugin.CustomPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class HeuraMinigame extends CustomPlugin {

    @Getter
    private static HeuraMinigame instance;

    private Game game;

    @Override
    public void enable() {
        instance = this;
        Configuration.loadConfig(new YamlFile("config.yml", this.getDataFolder().getAbsolutePath(), null, this), Config.values());
        Configuration.loadConfig(new YamlFile("messages.yml", this.getDataFolder().getAbsolutePath(), null, this), Messages.values());
        GameData.load();
        this.game = new Game();
        if (!GameData.getInstance().isSetup()) {
            game.setGameMode(MinigameMode.SETUP);
        }
        registerEvents(new ChatListener(), new BlockBreakListener(), new BlockPlaceListener(), new PlayerJoinListener(), new PlayerDamageListener(), new ExplosionListener(), new PlayerDamageByEntityListener(), new PlayerQuitListener());
    }

    @Override
    public void disable() {
        game.revertAllBlocks();
    }

    public void refreshGame() {
        this.game = new Game();
    }
}
