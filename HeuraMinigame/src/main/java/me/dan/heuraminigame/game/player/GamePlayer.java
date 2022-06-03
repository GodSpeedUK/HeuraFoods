package me.dan.heuraminigame.game.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.dan.heuraminigame.configuration.Config;
import me.dan.pluginapi.message.Placeholder;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class GamePlayer {

    private final UUID uuid;

    private boolean dead = false;

    //TODO
    public void kill() {
    }

    //TODO
    public void respawn() {

    }


    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

}
