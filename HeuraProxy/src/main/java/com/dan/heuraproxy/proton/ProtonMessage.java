package com.dan.heuraproxy.proton;

import me.drepic.proton.common.ProtonProvider;

public abstract class ProtonMessage<T> {


    public ProtonMessage() {
        ProtonProvider.get().registerMessageHandlers(this);
    }

    public abstract String getSubject();

    public void send(T type) {
        ProtonProvider.get().broadcast("HeuraMinigame", getSubject(), type);
    }


}

