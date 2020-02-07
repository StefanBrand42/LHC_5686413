package infrastructure.lhc.event;

import infrastructure.lhc.ExperimentScope;
import infrastructure.lhc.InitialEnergy;

public class RunExperimentPartialEvent {
    private InitialEnergy initialEnergy;
    private ExperimentScope experimentScope;

    public RunExperimentPartialEvent(InitialEnergy initialEnergy, ExperimentScope experimentScope) {
        this.initialEnergy = initialEnergy;
        this.experimentScope = experimentScope;
    }

    public InitialEnergy getInitialEnergy() {
        return initialEnergy;
    }

    public ExperimentScope getExperimentScope() {
        return experimentScope;
    }
}


