package me.dan.heuraminigame.listener;

import me.dan.heuraminigame.HeuraMinigame;
import me.dan.heuraminigame.game.MinigameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!HeuraMinigame.getInstance().getGame().getMinigameMode().equals(MinigameMode.RUNNING)) {
            e.setCancelled(true);
        }
    }

}
