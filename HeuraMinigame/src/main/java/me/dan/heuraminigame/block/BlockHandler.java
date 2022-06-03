package me.dan.heuraminigame.block;

import me.dan.heuraminigame.HeuraMinigame;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

public class BlockHandler {

    public static boolean handleBlock(Block block) {
        if (HeuraMinigame.getInstance().getGame().isQueuing()) {
            return true;
        }

        Material material = block.getType();
        BlockData data = block.getBlockData();

        BlockType blockType = new BlockType(material, data);

        if (HeuraMinigame.getInstance().getGame().hasBlock(block)) {
            return false;
        }

        HeuraMinigame.getInstance().getGame().addBlock(block, blockType);
        return false;
    }

    public static void revertBlock(Block block, BlockType blockType) {
        blockType.revert(block);
    }

}
