package me.dan.heuraminigame.listener;

import me.dan.heuraminigame.block.BlockHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        e.setCancelled(BlockHandler.handleBlock(e.getBlock()));
    }

}
