package com.dan.heuraproxy.server;

import com.dan.heuraproxy.proton.impl.GameServerUpdate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GameServer {

    private int players = 0;

    private final int maxPlayers;

    private final String id;

    private boolean queueing = true;

    public void setPlayers(int players) {
        this.players = players;
        GameServerUpdate.getInstance().updateServer(this);
    }

    public void setQueuing(boolean queuing) {
        this.queueing = queuing;
        GameServerUpdate.getInstance().updateServer(this);
    }


}
