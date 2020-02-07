package infrastructure;

import human_Resource.*;
import human_Resource.HrDep.HRAssistant;
import human_Resource.HrDep.HRDepartment;
import infrastructure.security.SecurityCenter;
import main.FingerAbGenerator;
import main.IdPerson;
import main.IrisGenerator;

import java.util.HashMap;
import java.util.Map;

public enum Employee_Managment implements IEmpoyee_Managment {
    instance;
    private Map<Integer, Employee> employeeMap;

    Employee_Managment() {
        employeeMap = new HashMap<>();

    }


    public void createEmployee(String name, String type , ResearchGroup researchGroup){
        switch (type){
            case "Researcher":
                int id = IdPerson.instance.getIdPerson();
                Researcher researcher1 = new Researcher(id,name, IrisGenerator.instance.irisCreate(),FingerAbGenerator.instance.fingerAbCreater(),false,false,false,false);
                researchGroup.addResearcher(researcher1);
                // in der Hash Tabelle speichern
                employeeMap.put(id,researcher1);
                break;
            case "ScientificAssistant" :
                int id2 = IdPerson.instance.getIdPerson();
                ScientificAssistant scientificAssistant1 = new ScientificAssistant(id2,name,IrisGenerator.instance.irisCreate(),FingerAbGenerator.instance.fingerAbCreater(),false,false,false,"01.01.2020","31.12.2020");
                researchGroup.addScientificAssistant(scientificAssistant1);
                employeeMap.put(id2,scientificAssistant1);
                break;
            case "SecurityOfficer":
                int id3 = IdPerson.instance.getIdPerson();
                SecurityOfficer securityOfficer01 = new SecurityOfficer(id3, name,IrisGenerator.instance.irisCreate(),FingerAbGenerator.instance.fingerAbCreater(),false,false,false,false);
                SecurityCenter.instance.addSecuriyOfficer(securityOfficer01);
                employeeMap.put(id3,securityOfficer01);
                break;
            case "ReceptionWorker":
                int id4 = IdPerson.instance.getIdPerson();
                ReceptionWorker receptionWorker = new ReceptionWorker(id4,name,IrisGenerator.instance.irisCreate(),FingerAbGenerator.instance.fingerAbCreater(),false,false,false);
                Reception.instance.addReceptionWorker(receptionWorker);
                employeeMap.put(id4,receptionWorker);
                break;
            case "HRAssistant":
                int id5 = IdPerson.instance.getIdPerson();
                HRAssistant hrAssistant = new HRAssistant(id5,name,IrisGenerator.instance.irisCreate(),FingerAbGenerator.instance.fingerAbCreater(),false,false,false);
                HRDepartment.instance.addHrAssistant(hrAssistant);
                employeeMap.put(id5,hrAssistant);
                break;






        }

    }

    public Map<Integer, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public void setEmployeeMap(Map<Integer, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }
}
