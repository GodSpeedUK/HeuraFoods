package me.dan.heuraminigame.listener;

import me.dan.heuraminigame.HeuraMinigame;
import me.dan.heuraminigame.configuration.Config;
import me.dan.heuraminigame.game.MinigameMode;
import me.dan.heuraminigame.game.player.GamePlayer;
import me.dan.pluginapi.message.Placeholder;
import me.dan.pluginapi.util.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (HeuraMinigame.getInstance().getGame().getMinigameMode().equals(MinigameMode.SETUP)) {
            return;
        }

        String format;

        if (HeuraMinigame.getInstance().getGame().getMinigameMode().equals(MinigameMode.QUEUEING)) {
            format = Config.CHAT_QUEUING.getString();
        } else {
            GamePlayer gamePlayer = HeuraMinigame.getInstance().getGame().getByUUID(e.getPlayer().getUniqueId());
            if (!gamePlayer.isDead()) {
                format = Config.CHAT_PLAYING.getString();
            } else {
                e.setCancelled(true);
                format = Config.CHAT_SPECTATOR.getString();
                format = Text.c(Placeholder.apply(format, new Placeholder("{player}", e.getPlayer().getName()), new Placeholder("{message}", e.getMessage())));
                for (GamePlayer gamePlayers : HeuraMinigame.getInstance().getGame().getAllPlayers()) {
                    if (gamePlayers.isDead()) {
                        gamePlayers.getPlayer().sendMessage(format);
                    }
                }
                return;
            }
        }

        format = Text.c(Placeholder.apply(format, new Placeholder("{player}", e.getPlayer().getName()), new Placeholder("{message}", e.getMessage())));
        e.setFormat(format);

    }

}
