package me.dan.heurahub.sign;

import com.dan.heuraproxy.HeuraProxy;
import com.dan.heuraproxy.server.GameServer;
import lombok.Getter;
import lombok.Setter;
import me.dan.heurahub.HeuraHub;
import me.dan.heurahub.configuration.Config;
import me.dan.pluginapi.file.gson.GsonUtil;
import me.dan.pluginapi.location.LocationWrapper;
import me.dan.pluginapi.message.Placeholder;
import me.dan.pluginapi.util.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;

@Getter
public class GameSign {

    private final LocationWrapper locationWrapper;
    private final String serverId;
    private final int id;

    @Setter
    private boolean queuing;

    public GameSign(int id, LocationWrapper locationWrapper, String serverId) {
        this.id = id;
        this.serverId = serverId;
        this.locationWrapper = locationWrapper;
        this.queuing = true;
    }

    public void startTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(HeuraHub.getInstance(), this::applyText, 20, 20);
    }

    public LocationWrapper getLocationWrapper() {
        return locationWrapper;
    }

    public String getServerId() {
        return serverId;
    }

    public void applyText() {
        Location location = locationWrapper.toBukkitLocation();
        if (!location.getBlock().getType().name().endsWith("SIGN")) {
//            FoodLib.getInstance().sendConsoleMessage("Sign for " + getServerId() + " isn't a sign!");
            return;
        }

        int players = -1;

        GameServer gameServer = HeuraProxy.getInstance().getGameServerManager().get(getServerId());

        if (gameServer == null) {
            return;
        }

        Sign sign = (Sign) location.getBlock();
        SignFormat signFormat = Config.SIGN_FORMAT.getSignFormat();
        Placeholder[] placeholders = new Placeholder[]{
                new Placeholder("{players}", players + ""),
                new Placeholder("{max_players}", gameServer.getMaxPlayers() + ""),
                new Placeholder("{queue}", queuing ? "Queue Open" : "Queue Closed")
        };
        for (int i = 0; i <= 3; i++) {
            String line = signFormat.getLines().get(i);
            line = Text.c(Placeholder.apply(line, placeholders));
            sign.setLine(i, line);
        }
    }

    public void save() {
        GsonUtil.save(HeuraHub.getInstance().getSignManager().getFolder(), id + "", this);
    }

    public int getId() {
        return id;
    }
}
