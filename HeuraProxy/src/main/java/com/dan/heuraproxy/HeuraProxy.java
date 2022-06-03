package com.dan.heuraproxy;

import com.dan.heuraproxy.proton.impl.GameServerUpdate;
import com.dan.heuraproxy.server.GameServerManager;
import lombok.Getter;
import me.dan.pluginapi.plugin.CustomPlugin;


@Getter
public final class HeuraProxy extends CustomPlugin {

    @Getter
    private static HeuraProxy instance;

    private GameServerManager gameServerManager;

    @Override
    public void enable() {
        instance = this;
        this.gameServerManager = new GameServerManager();
        new GameServerUpdate();
    }

    @Override
    public void disable() {

    }
}
