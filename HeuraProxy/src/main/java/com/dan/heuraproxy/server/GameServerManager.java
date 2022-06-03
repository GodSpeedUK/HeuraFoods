package com.dan.heuraproxy.server;

import me.dan.pluginapi.manager.Manager;

public class GameServerManager extends Manager<String, GameServer> {

    public GameServer getAppropriateServer() {
        if (getAll().isEmpty()) {
            return null;
        }

        GameServer mostPlayers = null;

        for (GameServer gameServer : getAll()) {
            if (!gameServer.isQueueing()) {
                continue;
            }

            if (mostPlayers == null || gameServer.getPlayers() > mostPlayers.getPlayers()) {
                mostPlayers = gameServer;
            }
        }

        return mostPlayers;
    }

}
