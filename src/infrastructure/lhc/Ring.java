package infrastructure.lhc;

import com.google.common.eventbus.Subscribe;
import infrastructure.ProtonTrapID;
import infrastructure.lhc.event.RunExperimentFullEvent;
import infrastructure.lhc.event.RunExperimentPartialEvent;

import java.util.ArrayList;

public class Ring extends Subscriber {
    private int energy;
    private boolean isActivade;
    private IExperiment currentExperiment;

    // für ein zu eins Beziehung
    private LargeHadronCollider largeHadronCollider;
    private Detector detector;

    // fuer ein zu zwei Beziehung
    private ProtonTrap protonTrap01;
    private ProtonTrap protonTrap02;
    //fuer Composition
    private ArrayList<Magnet> magnetArrayList;

    public Ring() {
        protonTrap01 = new ProtonTrap();
        protonTrap02 = new ProtonTrap();
        protonTrap01.setId(ProtonTrapID.A);
        protonTrap02.setId(ProtonTrapID.B);
        magnetArrayList = new ArrayList<>();
        for (int i = 0; i <72 ; i++) {
            magnetArrayList.add(new Magnet());

        }

    }

    // für den Bus
    @Subscribe
    public void receive(RunExperimentFullEvent startFull){

        System.out.println("---------Start RunExperimentFullEvent---------------- ");
        activate(startFull.getInitialEnergy());
        activateMagenticField();
        releaseProton();
        while (energy<300000){
            increaseEnergy(25000);
        }
        for (int i = 0; i <25 ; i++) {
            //Proton protontest = protonTrap01.getProtonQueue().remove();
            Proton proton1 = protonTrap01.getProtonQueue().remove();
            Proton proton2 = protonTrap02.getProtonQueue().remove();
            char[][][] test= proton1.getStructure();
            int idtest = proton1.getId();
            collide(proton1,proton2);
            IDector iDector = detector;
            iDector.addExperiment(currentExperiment);
            System.out.println(" Kollision erfolgreich angelegt von Proton:"+proton1.getId()+" und Proton"+proton2.getId());

        }
    }

    @Subscribe
    public void receive(RunExperimentPartialEvent startPartial){
        System.out.println("----Start RunExperimentPartialEvent------------------- ");
        activate(startPartial.getInitialEnergy());
        activateMagenticField();
        releaseProton();
        while (energy<300000){
            increaseEnergy(25000);
        }
        int i = 0;
        ExperimentScope experimentScope = startPartial.getExperimentScope();
        switch (experimentScope){
            case ES5:
                i = 5;
                break;
            case ES10:
                i=10;
                break;
            case ES20:
                i=20;
                break;
            case ESFull:
                i=25;
               break;
        }
        for (int j = 0; j <i ; j++) {
            //Proton protontest = protonTrap01.getProtonQueue().remove();
            Proton protonTrap1 = protonTrap01.getProtonQueue().remove();
            Proton protonTrap2 = protonTrap02.getProtonQueue().remove();
            collide(protonTrap1,protonTrap2);
            IDector iDector = detector;
            iDector.addExperiment(currentExperiment);
            System.out.println(" Kollision erfolgreich angelegt von Proton:"+protonTrap1.getId()+" und Proton"+protonTrap2.getId());


        }



    }









    public void setLargeHadronCollider(LargeHadronCollider largeHadronCollider) {
        this.largeHadronCollider = largeHadronCollider;
    }

    public void setDetector(Detector detector) {
        this.detector = detector;
    }

    public Detector getDetector() {
        return detector;
    }

    public ProtonTrap getProtonTrap01() {
        return protonTrap01;
    }

    public ProtonTrap getProtonTrap02() {
        return protonTrap02;
    }

    public void activate(){
        isActivade = true;
        energy = 0;
    }

    public  void activate(int intialEnergy){
        isActivade = true;
        energy = intialEnergy;
    }

    public  void activate(InitialEnergy initialEnergy){
        isActivade = true;
        switch (initialEnergy){
            case startEnergie25k:
                energy = 25000;
                break;
            case startEnergie50k:
                energy = 50000;
                break;
            case startEnergie100k:
                energy = 100000;
                break;
        }
    }


    public void activateMagenticField(){

    }
    public  void releaseProton(){


    }

    public void increaseEnergy(int delta){
        energy = energy+delta;
    }

    public void collide(Proton proton01, Proton proton02){
        Experiment experiment = new Experiment(proton01, proton02);
        currentExperiment = experiment;



    }
    public int decreaseEnergy(){
        return 0;

    }

    public void shutdown(){

    }



}
