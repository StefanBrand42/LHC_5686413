package infrastructure.security;

import human_Resource.Person;
import infrastructure.Permission;

import java.util.ArrayList;

public class IDCardEmployee extends  IDCard  implements IRoIDCardEmployee, IrfiDIdCardEmpoyee{
    // für Composition
    private ChipFingerAb chipFingerAb;

    public IDCardEmployee(String id, String validFrom, String validUntil, int[][] isrisStructure, ArrayList<Permission> permissionArrayList, boolean isLocked, String password, String fingerAbrduck, Person person) {
        super(id);
        setValidFrom(validFrom);
        setValidUntil(validUntil);
        this.irisStructure= isrisStructure;
        this.permissionList=permissionArrayList;
        this.isLocked= isLocked;
        this.getChip().setPassword(password);
        chipFingerAb = new ChipFingerAb();
        chipFingerAb.setFingerabruck(fingerAbrduck);
        setPerson(person);

    }

    @Override
    public ChipFingerAb getChipFingerAb() {
        return chipFingerAb;
    }
}
