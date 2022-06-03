package me.dan.heurahub.configuration;

import me.dan.heurahub.sign.SignFormat;
import me.dan.pluginapi.configuration.Configuration;

public enum Config implements Configuration {

    SIGN_FORMAT("sign-format", new SignFormat(
        "&bServer: {server}",
        "&b{players}/{max_players}",
        " ",
        "&bQueue {queue}"
    ));

    private final String path;
    private Object value;

    Config(String path, Object value) {
        this.path = path;
        this.value = value;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    public SignFormat getSignFormat() {
        return (SignFormat) value;
    }
}
