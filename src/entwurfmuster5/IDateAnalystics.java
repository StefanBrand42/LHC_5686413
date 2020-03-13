package entwurfmuster5;

import infrastructure.lhc.Block;

import java.util.Map;

public interface IDateAnalystics {
    Map<String, Block> getBlockMap();
    Block getBlock(String blockName);
}
