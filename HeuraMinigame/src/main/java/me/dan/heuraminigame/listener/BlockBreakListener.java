package me.dan.heuraminigame.listener;

import me.dan.heuraminigame.block.BlockHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(BlockHandler.handleBlock(e.getBlock()));
    }

}
