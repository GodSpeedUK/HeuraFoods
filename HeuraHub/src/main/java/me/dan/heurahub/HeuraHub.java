package me.dan.heurahub;

import lombok.Getter;
import me.dan.heurahub.listener.SignListener;
import me.dan.heurahub.sign.SignManager;
import me.dan.pluginapi.plugin.CustomPlugin;

@Getter
public final class HeuraHub extends CustomPlugin {

    @Getter
    private static HeuraHub instance;

    private SignManager signManager;


    @Override
    public void enable() {
        instance = this;
//        this.sql = new SQLClient(this, Config.MYSQL_HOST.getString(), Config.MYSQL_PORT.getInt(), Config.MYSQL_DATABASE.getString(), Config.MYSQL_USERNAME.getString(), Config.MYSQL_PASSWORD.getString());
        this.signManager = new SignManager();
        registerEvents(new SignListener());
    }

    @Override
    public void disable() {
        // Plugin shutdown logic
    }


}
