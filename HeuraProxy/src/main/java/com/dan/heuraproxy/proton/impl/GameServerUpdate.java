package com.dan.heuraproxy.proton.impl;

import com.dan.heuraproxy.HeuraProxy;
import com.dan.heuraproxy.proton.ProtonMessage;
import com.dan.heuraproxy.server.GameServer;
import lombok.Getter;
import me.drepic.proton.common.message.MessageHandler;

public class GameServerUpdate extends ProtonMessage<GameServer> {

    @Getter
    private static GameServerUpdate instance;

    public GameServerUpdate() {
        instance = this;
    }

    public void updateServer(GameServer gameServer) {
        super.send(gameServer);
    }

    @Override
    public String getSubject() {
        return "GameServerUpdate";
    }

    @MessageHandler(namespace = "HeuraMinigame", subject = "GameServerUpdate")
    public void onMessageReceive(GameServer gameServer) {
        HeuraProxy.getInstance().getGameServerManager().insert(gameServer.getId(), gameServer);
    }


}
