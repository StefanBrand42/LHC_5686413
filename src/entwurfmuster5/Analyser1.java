package entwurfmuster5;

import infrastructure.lhc.Block;

public class Analyser1 extends  Analyser {


    public void analyse(Block block){
        if (canAnalyse(block, WeightBlock.smallerThan2)){
            System.out.println("Analyser1 anlysliert den Block");

        }else {
            super.analyse(block);
        }
    }
}


