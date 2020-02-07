
package infrastructure;
import com.google.common.eventbus.EventBus;
import infrastructure.lhc.ExperimentScope;
import infrastructure.lhc.InitialEnergy;
import infrastructure.lhc.event.AnalyseEvent;
import infrastructure.lhc.event.RunExperimentFullEvent;
import infrastructure.lhc.Subscriber;
import infrastructure.lhc.event.RunExperimentPartialEvent;


import java.util.ArrayList;


public enum ControlCenter {
    instance;
    private final String roomID;
    private EventBus eventBus;
    private ArrayList<Workplace> workplaceArrayList;

    ControlCenter() {
        workplaceArrayList = new ArrayList<>();
        eventBus = new EventBus("ControlBus");
        roomID = "C01";
    }

    // BussEvent
    public void startExperiment(InitialEnergy initialEnergy){
        eventBus.post(new RunExperimentFullEvent(initialEnergy));

    }
    public void startExperiment(InitialEnergy initialEnergy, ExperimentScope experimentScope){
        eventBus.post(new RunExperimentPartialEvent(initialEnergy,experimentScope));
    }

    public void startAnalyse(){
        eventBus.post(new AnalyseEvent());
    }






    public void  addSubsriber(Subscriber subscribe){

        eventBus.register(subscribe);
    }

    public void addWorkplce(Workplace workplace){
        workplaceArrayList.add(workplace);
    }
}
