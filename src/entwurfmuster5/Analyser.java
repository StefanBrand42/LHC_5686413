package entwurfmuster5;

import infrastructure.lhc.Block;

public abstract class Analyser {
    private Analyser successor;
    public void analyse(Block block){
        if(getSuccessor() != null){
            getSuccessor().analyse(block);
        }else{
            System.out.println("unable to find correct Analyser");
        }
    }

    protected boolean canAnalyse(Block block, WeightBlock weightBlock){
       return (block == null) || (getWeight(block)== weightBlock);
    }



    public Analyser getSuccessor() {
        return successor;
    }

    public void setSuccessor(Analyser successor) {
        this.successor = successor;
    }

    public WeightBlock getWeight(Block block){
        String structeBlock = block.getStructure();
        int anzahlAbisz = structeBlock.split("[A-Z]").length;
        int anzahlSonderzeichen= 0;
        for (int j = 0; j < structeBlock.length(); j++){
            if (!(((int)structeBlock.charAt(j)>=65 && (int)structeBlock.charAt(j)<=89) || ((int)structeBlock.charAt(j)>=97 && (int)structeBlock.charAt(j)<=122)) )
            {
               anzahlSonderzeichen++;
            }
        }
        if (anzahlSonderzeichen== 0){
            return WeightBlock.largerThan2;
        }
        double weigh = anzahlAbisz/anzahlSonderzeichen;
        if (weigh<2){
            return WeightBlock.smallerThan2;
        }else{
            return WeightBlock.largerThan2;
        }




    }


}
