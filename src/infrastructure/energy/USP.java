package infrastructure.energy;

import java.util.ArrayList;

public class USP {
    private boolean isStandBy;
    private boolean isActivated;

    // fuer association
    private ArrayList<Battery> batteryArrayList;

    public USP() {
        batteryArrayList = new ArrayList<>();
        for (int i = 0; i <25 ; i++) {
            batteryArrayList.add(new Battery());
        }
    }

    public void determineChargeState(){

    }

    public void charge(ThreePinPlug plug){

    }

    public int takeOut(){
        return 0;
    }

    public ArrayList<Battery> getBatteryArrayList() {
        return batteryArrayList;
    }
}
