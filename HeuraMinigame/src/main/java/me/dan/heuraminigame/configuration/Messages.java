package me.dan.heuraminigame.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.dan.pluginapi.configuration.Configuration;
import me.dan.pluginapi.message.Message;
import me.dan.pluginapi.message.TitleMessage;

import java.util.List;

@AllArgsConstructor
public enum Messages implements Configuration, Message {

    PREFIX("prefix", "&b&l[&3!&b&l] &3"),
    TEAM_FULL("error.team-full", "{prefix}The Team {team}&3 is full!"),
    ALREADY_IN_TEAM("error.already-in-team", "{prefix}You are already in the {team} &3team!"),
    TEAM_JOINED("team-joined", "{prefix}You have joined the {team}&3 Team!"),
    GAME_NOT_QUEUING("error.game-not-queuing", "{prefix}The game is no longer queuing!"),
    GAME_FULL("error.game-full", "{prefix}The game is full!"),
    FRIENDLY_FIRE("error.friendly-fire", "{prefix}You cannot attack your own team!"),
    PLAYER_JOINED("player-joined", "{prefix}{player} has joined the game! &7(&3{players}/{max_players}&7)"),
    PLAYER_LEFT("player-left", "{prefix}{player} has left the game! &7(&3{players}/{max_players}&7)"),
    GAME_COUNTDOWN("game.countdown", new TitleMessage(
            "&3&lGame Starting...",
            "&bThe game will start in &9{seconds} &bSeconds!"
    )),
    GAME_COUNTDOWN_CHAT("game.countdown-chat", "{prefix}The game will start in &8{seconds} &3Seconds!"),
    GAME_COUNTDOWN_CANCELLED("game.countdown-cancelled", new TitleMessage(
            "&3&lCountdown &c&lCANCELLED",
            "&bCountdown cancelled due to there not being enough players!"
    )),
    GAME_STARTED("game.started", new TitleMessage(
            "&3&lGame Started!",
            "&bYou are on the {team} &bTeam!"
    )),
    SETUP_MODE_KICK("setup-mode-kick",
            "&b&lSETUP MODE ENABLED \n" +
                    "&3You were removed from the server due to setup mode enabling!"
    ),
    SPAWN_SET("spawn-set", "{prefix}Spawn location for {location} set!"),
    SPAWN_NOT_A_LOCATION("errors.spawn-not-a-location", "{prefix}{location} is not a Location!"),
    SETUP_MODE_TOGGLE("setup-mode-toggle", "{prefix}Setup mode set to: {toggle}"),
    GAME_OVER("game.over", new TitleMessage(
            "&3&lGAME OVER",
            "{team} &ateam has won the game!",
            80
    )),
    SERVER_RESET("server-reset", new TitleMessage("&b&lRESETTING", "&3The server will reset in &910 &3seconds!"));

    @Getter
    private final String path;
    @Getter
    @Setter
    private Object value;

    @Override
    public List<String> getStringList() {
        return Configuration.super.getStringList();
    }

    @Override
    public String getString() {
        return Configuration.super.getString();
    }

    @Override
    public String getPrefix() {
        return PREFIX.getString();
    }


}
