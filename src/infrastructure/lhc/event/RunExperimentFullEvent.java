package infrastructure.lhc.event;

import infrastructure.lhc.ExperimentScope;
import infrastructure.lhc.InitialEnergy;

public class RunExperimentFullEvent {
    private InitialEnergy initialEnergy;


    public RunExperimentFullEvent(InitialEnergy initialEnergy) {
        this.initialEnergy = initialEnergy;

    }

    public InitialEnergy getInitialEnergy() {
        return initialEnergy;
    }
}
