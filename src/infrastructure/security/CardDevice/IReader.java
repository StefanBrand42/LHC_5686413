package infrastructure.security.CardDevice;

import human_Resource.Employee;
import human_Resource.Person;
import infrastructure.Permission;
import infrastructure.security.*;

import java.util.ArrayList;
import java.util.Date;

public interface IReader extends ICardDevice {
    void insertIDCardVistor(IIDCard idCard);
    void insterIDCardEmpl(IRoIDCardEmployee idCard);
    void rFIDiDCardVistor(IrFIDIdCardVisitor idCard);
    void rFIDidCardEmployee(IrfiDIdCardEmpoyee idCard);











}
