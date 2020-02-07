package infrastructure.lhc;

import infrastructure.security.CardDevice.Reader;

import java.util.LinkedList;

public interface IRODetector {
    String getHiggsBosonStructure();
    boolean isAcctivated();
    LinkedList<IExperiment> getExperimentList();
    Reader getRedaer();

}


