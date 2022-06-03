package me.dan.heuraminigame.listener;

import me.dan.heuraminigame.HeuraMinigame;
import me.dan.heuraminigame.game.MinigameMode;
import me.dan.heuraminigame.game.player.GamePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageByEntityListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {


        if (!HeuraMinigame.getInstance().getGame().isPvp()) {
            e.setCancelled(true);
            return;
        }

        if (!HeuraMinigame.getInstance().getGame().getMinigameMode().equals(MinigameMode.RUNNING)) {
            e.setCancelled(true);
            return;
        }

        GamePlayer player = HeuraMinigame.getInstance().getGame().getByUUID(e.getEntity().getUniqueId());

        if (player == null) {
            e.setCancelled(true);
            return;
        }

        GamePlayer damager = HeuraMinigame.getInstance().getGame().getByUUID(e.getDamager().getUniqueId());


    }


}
