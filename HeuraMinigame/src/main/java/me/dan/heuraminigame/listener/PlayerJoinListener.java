package me.dan.heuraminigame.listener;

import me.dan.heuraminigame.HeuraMinigame;
import me.dan.heuraminigame.configuration.Config;
import me.dan.heuraminigame.configuration.Messages;
import me.dan.heuraminigame.data.GameData;
import me.dan.heuraminigame.game.MinigameMode;
import me.dan.pluginapi.message.Placeholder;
import me.dan.pluginapi.util.Text;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        if (!HeuraMinigame.getInstance().getGame().getMinigameMode().equals(MinigameMode.QUEUEING)) {
            if (e.getPlayer().hasPermission("HeuraMinigame.join-bypass")) {
                return;
            }

            e.getPlayer().kickPlayer(Text.c(Placeholder.apply(Messages.GAME_NOT_QUEUING.getString(), new Placeholder("{prefix}", Messages.PREFIX.getString()))));
            return;
        }

        if (HeuraMinigame.getInstance().getGame().getPlayers() >= Config.MAX_PLAYERS.getInt()) {
            if (e.getPlayer().hasPermission("HeuraMinigame.join-bypass")) {
                return;
            }
            e.getPlayer().kickPlayer(Text.c(Placeholder.apply(Messages.GAME_FULL.getString(), new Placeholder("{prefix}", Messages.PREFIX.getString()))));
        }

        HeuraMinigame.getInstance().getGame().registerPlayer(e.getPlayer());
        e.setJoinMessage(Text.c(Placeholder.apply(Messages.PLAYER_JOINED.getString(),
                new Placeholder("{prefix}", Messages.PREFIX.getString()),
                new Placeholder("{max_players}", Config.MAX_PLAYERS.getInt() + ""),
                new Placeholder("{players}", HeuraMinigame.getInstance().getGame().getPlayers() + ""),
                new Placeholder("{player}", e.getPlayer().getName()))));

        if (GameData.getInstance().getLobbyLocation() != null) {
            e.getPlayer().teleport(GameData.getInstance().getLobbyLocation());
        }


    }

}
