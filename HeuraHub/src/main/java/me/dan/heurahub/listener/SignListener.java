package me.dan.heurahub.listener;

import me.dan.heurahub.HeuraHub;
import me.dan.heurahub.sign.GameSign;
import me.dan.pluginapi.location.LocationWrapper;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {

    @EventHandler
    public void onSignEdit(SignChangeEvent e) {
        if (!e.getLine(0).equalsIgnoreCase("{gamesign}")) {
            return;
        }

        if (e.getLine(1) == null) {
            return;
        }

        String server = e.getLine(1);

        Location location = e.getBlock().getLocation();

        LocationWrapper locationWrapper = new LocationWrapper(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());

        GameSign gameSign = new GameSign(HeuraHub.getInstance().getSignManager().getNextFreeId(), locationWrapper, server);

        gameSign.save();

        gameSign.startTask();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            return;
        }

        Block block = e.getClickedBlock();

        if(!block.getType().name().contains("SIGN")){
            return;
        }

        Location location = e.getClickedBlock().getLocation();

        LocationWrapper locationWrapper = new LocationWrapper(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());

        GameSign gameSign = HeuraHub.getInstance().getSignManager().getByLocation(locationWrapper);

        if(gameSign == null){
            return;
        }

        if(!gameSign.isQueuing()){
            return;
        }

        //TODO
//        FoodLib.getInstance().getBungeeChannelApi().connect(e.getPlayer(), gameSign.getServerId());

    }

}
