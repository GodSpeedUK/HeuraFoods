package me.dan.heuraminigame.listener;

import me.dan.heuraminigame.block.BlockHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class ExplosionListener implements Listener {

    @EventHandler
    public void onExplode(BlockExplodeEvent e) {
        BlockHandler.handleBlock(e.getBlock());
    }

}
