package infrastructure.lhc;

public class Proton {
    private int id;
    private char[][][] structure;
    private double weight;

    private String proton1Mio;

    public Proton(int id, char[][][] structure) {
        this.id = id;
        this.structure = structure;
    }

    public int getId() {
        return id;
    }

    public char[][][] getStructure() {
        return structure;
    }
}
