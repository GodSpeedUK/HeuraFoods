package me.dan.heurahub.sign;


import me.dan.heurahub.HeuraHub;
import me.dan.pluginapi.file.gson.GsonUtil;
import me.dan.pluginapi.location.LocationWrapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SignManager {

    private final File folder;

    private final Map<Integer, GameSign> signMap;

    public SignManager() {
        this.folder = new File(HeuraHub.getInstance().getDataFolder(), "signs");
        if (!this.folder.exists()) {
            folder.mkdirs();
        }
        this.signMap = new HashMap<>();
        loadSigns();
    }

    public File getFolder() {
        return folder;
    }


    private void loadSigns() {
        if (folder.listFiles() == null) {
            return;
        }
        for (File file : folder.listFiles()) {
            String[] arrayOfFile = file.getName().split("\\.");
            if (arrayOfFile.length > 2) {
                return;
            }
            if (!arrayOfFile[1].equalsIgnoreCase("json")) {
                return;
            }

            String id = arrayOfFile[0];
            int idInt;
            try {
                idInt = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                return;
            }

            GameSign gameSign = GsonUtil.read(folder, id + ".json", GameSign.class);

            gameSign.startTask();

            signMap.put(idInt, gameSign);
        }
    }

    public int getNextFreeId() {
        if (signMap.isEmpty()) {
            return 1;
        }
        int i = 1;
        boolean found = false;
        while (!found) {
            if (signMap.containsKey(i)) {
                i++;
                continue;
            }
            found = true;
        }
        return i;
    }

    public void registerSign(GameSign gameSign) {
        signMap.put(gameSign.getId(), gameSign);
    }

    public GameSign getByLocation(LocationWrapper locationWrapper) {
        for (GameSign gameSign : signMap.values()) {
            if (gameSign.getLocationWrapper().equals(locationWrapper)) {
                return gameSign;
            }
        }
        return null;
    }

}
