package infrastructure.lhc;

import infrastructure.energy.USP;

public class LargeHadronCollider {

    // für ein zu ein Beziehung
    private Building building;
    private Ring ring;
    // fuer ein zu zwei beZiehung
    private USP usp01;
    private USP usp02;

    public LargeHadronCollider() {
        usp01 = new USP();
        usp02 = new USP();

    }

    public void setBuilding(Building building) {

        this.building = building;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public Building getBuilding() {
        return building;
    }

    public Ring getRing() {
        return ring;
    }

    public USP getUsp01() {
        return usp01;
    }

    public USP getUsp02() {
        return usp02;
    }
}
