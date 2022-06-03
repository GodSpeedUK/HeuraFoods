package me.dan.heuraminigame.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.configuration.Configuration;
import me.dan.pluginapi.item.Item;
import me.dan.pluginapi.menu.Menu;
import me.dan.pluginapi.menu.MenuItem;
import me.dan.pluginapi.util.Pair;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;

@AllArgsConstructor
public enum Config implements Configuration {

    SERVER_NAME("server-name", "ServerName"),
    DEFAULT_LIVES("game.default-lives", 10),
    MAX_PLAYERS("game.max-players", 20),
    MIN_PLAYERS("game.min-players", 1),
    RESPAWN_TIME("game.respawn-time", 5),
    CHAT_SPECTATOR("chat.spectator", "&7[SPECTATOR] &3{player} &8» &7{message}"),
    CHAT_QUEUING("chat.queuing", "&7{player} &8» &7{message}"),
    CHAT_PLAYING("chat.playing", "&7{player} &8» &7{message}"),
    TEST_MENU("test-menu", new Menu()
            .setName("&7God Tier Menu")
            .setSize(27)
            .addItems(
                    new MenuItem()
                            .setItem(
                                    Item.builder()
                                            .material("white_stained_glass_pane")
                                            .name(" ")
                                            .itemFlags(Arrays.asList(
                                                    ItemFlag.HIDE_ENCHANTS,
                                                    ItemFlag.HIDE_ATTRIBUTES
                                            ))
                                            .enchantments(
                                                    Arrays.asList(
                                                            new Pair<>(Enchantment.ARROW_INFINITE, 1)
                                                    )
                                            ).build()
                            ).setFill(true)
                            .setKey("filler")
            )),
    SCOREBOARD("scoreboard", Arrays.asList(
            "&8&l============",
            " ",
            "&3» &bTeam: {team}",
            "&3» &bLives: {lives}",
            "&3» &bTeam Lives: {team_lives}",
            " ",
            "&8&l============"
    ));

    @Getter
    private final String path;

    @Getter
    @Setter
    private Object value;

}
