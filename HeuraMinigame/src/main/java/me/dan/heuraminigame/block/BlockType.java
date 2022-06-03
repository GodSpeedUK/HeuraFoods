package me.dan.heuraminigame.block;

import lombok.Data;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

@Data
public class BlockType {

    private final Material material;
    private final BlockData blockData;

    public void revert(Block block) {
        block.setType(material);
        block.setBlockData(blockData);
    }

}
