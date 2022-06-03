package me.dan.heuraminigame.game;

import com.dan.heuraproxy.server.GameServer;
import lombok.Getter;
import lombok.Setter;
import me.dan.heuraminigame.HeuraMinigame;
import me.dan.heuraminigame.block.BlockHandler;
import me.dan.heuraminigame.block.BlockType;
import me.dan.heuraminigame.configuration.Config;
import me.dan.heuraminigame.configuration.Messages;
import me.dan.heuraminigame.game.player.GamePlayer;
import me.dan.pluginapi.message.Placeholder;
import me.dan.pluginapi.util.Text;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public class Game {

    private final Map<Block, BlockType> modifiedBlocks;

    private final GameServer gameWrapper;

    private final Map<UUID, GamePlayer> gamePlayerMap;

    private int startTask = -1;

    private int secondsUntilStart = 30;

    private final List<GamePlayer> playersActive;

    @Setter
    @Getter
    private boolean pvp = false;

    @Getter
    private MinigameMode minigameMode = MinigameMode.QUEUEING;

    @Setter
    private GameRound gameRound;

    public void setGameMode(MinigameMode minigameMode) {
        this.minigameMode = minigameMode;
        if (!minigameMode.equals(MinigameMode.QUEUEING)) {
            gameWrapper.setQueuing(false);
            return;
        }

        gameWrapper.setQueuing(true);
    }

    public Game() {
        this.playersActive = new ArrayList<>();
        this.gameWrapper = new GameServer(Config.SERVER_NAME.getString(), Config.MAX_PLAYERS.getInt());
        this.modifiedBlocks = new HashMap<>();
        this.gamePlayerMap = new HashMap<>();
    }

    public void setPlayers(int players) {
        gameWrapper.setPlayers(players);
    }

    @Deprecated
    public boolean isQueuing() {
        return gameWrapper.isQueueing();
    }

    public int getPlayers() {
        return gameWrapper.getPlayers();
    }

    public void addBlock(Block block, BlockType blockType) {
        modifiedBlocks.put(block, blockType);
    }

    public boolean hasBlock(Block block) {
        return modifiedBlocks.containsKey(block);
    }

    public void revertAllBlocks() {
        for (Block block : modifiedBlocks.keySet()) {
            BlockHandler.revertBlock(block, modifiedBlocks.get(block));
        }
    }

    public GamePlayer getByUUID(UUID uuid) {
        return gamePlayerMap.get(uuid);
    }


    public void clearData(GamePlayer gamePlayer) {
        gamePlayerMap.remove(gamePlayer.getUuid());
    }

    public void registerPlayer(Player player) {
        this.gamePlayerMap.put(player.getUniqueId(), new GamePlayer(player.getUniqueId()));
        refreshPlayers();
        if (getPlayers() >= Config.MIN_PLAYERS.getInt() && startTask == -1) {
            beginStartTimer();
        }
    }

    public void unregisterPlayer(Player player) {
        this.gamePlayerMap.remove(player.getUniqueId());
        refreshPlayers();
        if (getPlayers() <= Config.MIN_PLAYERS.getInt() && startTask != -1) {
            cancelStart();
        }
    }

    private void refreshPlayers() {
        gameWrapper.setPlayers(gamePlayerMap.size());
    }


    public void beginStartTimer() {
        this.startTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(HeuraMinigame.getInstance(), () -> {
            if (secondsUntilStart == 30 || secondsUntilStart <= 10) {
                sendCountdownMessage();
            }

            if (secondsUntilStart <= 0) {
                start();
            }

            secondsUntilStart--;
        }, 20, 20);
    }

    public void start() {
        if (startTask != -1) {
            Bukkit.getScheduler().cancelTask(startTask);
            startTask = -1;
            setGameMode(MinigameMode.RUNNING);
            secondsUntilStart = 30;
        }
        setGameMode(MinigameMode.RUNNING);

    }

    private void sendCountdownMessage() {
        Messages.GAME_COUNTDOWN.broadcast(new Placeholder("{seconds}", secondsUntilStart + ""));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
    }

    public void cancelStart() {
        if (startTask != -1) {
            Bukkit.getScheduler().cancelTask(startTask);
            startTask = -1;
            Messages.GAME_COUNTDOWN_CANCELLED.broadcast();
            secondsUntilStart = 30;
        }
    }


    public Collection<GamePlayer> getAllPlayers() {
        return gamePlayerMap.values();
    }

    public void finish() {
        GamePlayer gamePlayer = playersActive.get(0);
        Messages.GAME_OVER.broadcastTitle(new Placeholder("{team}", gamePlayer.getPlayer().getName()));
        Bukkit.getScheduler().runTaskLater(HeuraMinigame.getInstance(), () -> {
            Messages.SERVER_RESET.broadcastTitle();
            commenceReset();
        }, Messages.GAME_OVER.getTitleMessage().getDuration());
    }

    private void commenceReset() {
        Bukkit.getScheduler().runTaskLater(HeuraMinigame.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer(Text.c("&bThis game has concluded!"));
            }
            setGameMode(MinigameMode.RESETTING);
            revertAllBlocks();
            HeuraMinigame.getInstance().refreshGame();
        }, 200);
    }

    private void applyScoreBoard(Player player) {
        GamePlayer gamePlayer = getByUUID(player.getUniqueId());
        if (gamePlayer == null) {
            return;
        }

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();


    }

}
