package human_Resource;

import human_Resource.Person;
import infrastructure.security.IDCard;
import infrastructure.security.IIDCard;
import infrastructure.security.IROIDCard;
import infrastructure.security.IRoIDCardEmployee;

public abstract class Employee  extends Person {
    protected String fingerAb;
    protected boolean isManager;
    protected boolean isMentor;
    protected boolean hasBugetResponsibiltiy;
    protected IRoIDCardEmployee idCard;


    public Employee(int id, String name, int[][] iris, String fingerAb, boolean isManager, boolean isMentor, boolean hasBugetResponsibiltiy) {
        super(id, name, iris);
        this.fingerAb = fingerAb;
        this.isManager = isManager;
        this.isMentor = isMentor;
        this.hasBugetResponsibiltiy = hasBugetResponsibiltiy;
    }

    public void setIdCard(IRoIDCardEmployee idCard) {
        this.idCard = idCard;
    }

    public String getFingerAb() {
        return fingerAb;
    }

    public IRoIDCardEmployee getIdCard() {
        return idCard;
    }
}
