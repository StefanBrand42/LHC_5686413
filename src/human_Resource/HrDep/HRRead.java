package human_Resource.HrDep;

import human_Resource.Employee;
import infrastructure.Employee_Managment;
import infrastructure.IROEmployye_Managment;

public abstract class  HRRead  extends Employee {
    IROEmployye_Managment iroEmployye_managment;

    public HRRead(int id, String name, int[][] iris, String fingerAb, boolean isManager, boolean isMentor, boolean hasBugetResponsibiltiy) {
        super(id, name, iris, fingerAb, isManager, isMentor, hasBugetResponsibiltiy);
        this.iroEmployye_managment = Employee_Managment.instance;
    }




    public IROEmployye_Managment getIroEmployye_managment() {
        return iroEmployye_managment;
    }

}
