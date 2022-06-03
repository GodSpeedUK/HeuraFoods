package me.dan.heuraminigame.listener;

import me.dan.heuraminigame.HeuraMinigame;
import me.dan.heuraminigame.configuration.Config;
import me.dan.heuraminigame.configuration.Messages;
import me.dan.pluginapi.message.Placeholder;
import me.dan.pluginapi.util.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        HeuraMinigame.getInstance().getGame().unregisterPlayer(e.getPlayer());
        e.setQuitMessage(Text.c(Placeholder.apply(Messages.PLAYER_LEFT.getString(),
                new Placeholder("{prefix}", Messages.PREFIX.getString()),
                new Placeholder("{max_players}", Config.MAX_PLAYERS.getInt() + ""),
                new Placeholder("{players}", HeuraMinigame.getInstance().getGame().getPlayers() + ""),
                new Placeholder("{player}", e.getPlayer().getName()))));
    }

}
