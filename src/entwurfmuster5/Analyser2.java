package entwurfmuster5;

import infrastructure.lhc.Block;

public class Analyser2 extends  Analyser{

    public Analyser2(Analyser sucessor) {
        this.setSuccessor(sucessor);
    }

    public void analyse(Block block){
        if (canAnalyse(block, WeightBlock.largerThan2)){
            System.out.println("Analyser2 anlysliert den Block");

        }else {
            super.analyse(block);
        }
    }


}
