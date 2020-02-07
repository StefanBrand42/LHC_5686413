package infrastructure.lhc;

import infrastructure.lhc.LargeHadronCollider;

public class Building {
    private final  String owner;
    private final String location;




    // fuer ein zu ein Beziehung
    private LargeHadronCollider largeHadronCollider;

    public Building() {
        owner = "CERN";
        location = "Geneva";


    }

    public void setLargeHadronCollider(LargeHadronCollider largeHadronCollider) {
        this.largeHadronCollider = largeHadronCollider;
    }

    public LargeHadronCollider getLargeHadronCollider() {
        return largeHadronCollider;
    }
}
