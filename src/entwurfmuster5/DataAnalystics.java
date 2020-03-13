package entwurfmuster5;

import infrastructure.lhc.Block;
import infrastructure.lhc.IExperiment;

import java.util.HashMap;
import java.util.Map;

public class DataAnalystics implements IDateAnalystics {
    private Map<String, Block> blockMap;

    public DataAnalystics() {
        blockMap= new HashMap<>();
    }

    public Block getBlock(String blockName){
        Block block = blockMap.get(blockName);
        if (block == null){
            block = new Block(blockName);
            blockMap.put(blockName,block);
        }

        return block;
    }

    public Map<String, Block> getBlockMap() {
        return blockMap;
    }
}
