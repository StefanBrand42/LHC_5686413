package human_Resource;

import human_Resource.Employee;
import infrastructure.lhc.IRODetector;

public class Researcher extends Employee {
    private boolean isSenoir;

    public Researcher(int id, String name, int[][] iris, String fingerAb, boolean isManager, boolean isMentor, boolean hasBugetResponsibiltiy, boolean isSenoir) {
        super(id, name, iris, fingerAb, isManager, isMentor, hasBugetResponsibiltiy);
        this.isSenoir = isSenoir;

    }
}
