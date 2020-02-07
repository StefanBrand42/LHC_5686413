package human_Resource.HrDep;

import human_Resource.Employee;
import infrastructure.Employee_Managment;
import infrastructure.IEmpoyee_Managment;

public abstract class HRWrite extends Employee {
    IEmpoyee_Managment iEmpoyee_managment;

    public HRWrite(int id, String name, int[][] iris, String fingerAb, boolean isManager, boolean isMentor, boolean hasBugetResponsibiltiy) {
        super(id, name, iris, fingerAb, isManager, isMentor, hasBugetResponsibiltiy);
        this.iEmpoyee_managment = Employee_Managment.instance;
    }



    public IEmpoyee_Managment getiEmpoyee_managment() {
        return iEmpoyee_managment;
    }
}
