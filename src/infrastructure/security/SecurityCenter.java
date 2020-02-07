package infrastructure.security;

import human_Resource.Employee;
import human_Resource.SecurityOfficer;
import infrastructure.Employee_Managment;
import infrastructure.IROEmployye_Managment;
import infrastructure.security.CardDevice.IWriter;
import infrastructure.security.CardDevice.IWriterEmployee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum  SecurityCenter {
    instance;
    private ArrayList<SecurityOfficer> securityOfficerArrayList;
    private IWriterEmployee writer;

    SecurityCenter() {
        securityOfficerArrayList = new ArrayList<>();
    }

    public void addSecuriyOfficer(SecurityOfficer securityOfficer){
        securityOfficerArrayList.add(securityOfficer);
    }

    public void setWriter(IWriterEmployee writer) {
        this.writer = writer;
    }

    public ArrayList<SecurityOfficer> getSecurityOfficerArrayList() {
        return securityOfficerArrayList;
    }

    public IWriterEmployee getWriter() {
        return writer;
    }

    //public Map<Integer, Employee> getEmployeeMap() {
//        return Employee_Managment.instance.getEmployeeMap();
//    }

    public IROEmployye_Managment getIroEmployye_managment() {
        return (IROEmployye_Managment) Employee_Managment.instance.getEmployeeMap();
    }



}
