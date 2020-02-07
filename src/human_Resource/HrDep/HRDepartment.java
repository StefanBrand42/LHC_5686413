package human_Resource.HrDep;

import human_Resource.ReceptionWorker;

import java.util.ArrayList;

public enum HRDepartment {
    instance;
    private ArrayList<HRHoD> hrHoDArrayList;
    private ArrayList<HRConsultant> hrConsultantArrayList;
    private ArrayList<HRAssistant> hrAssistantArrayList;

    HRDepartment() {
        hrAssistantArrayList= new ArrayList<>();
        hrConsultantArrayList = new ArrayList<>();
        hrHoDArrayList = new ArrayList<>();
    }

    public void addHrHoD(HRHoD hrHoD){
        hrHoDArrayList.add(hrHoD);
    }

    public void addHrAssistant(HRAssistant hrAssistant){
        hrAssistantArrayList.add(hrAssistant);
    }

    public void addHrConsultant(HRConsultant hrConsultant){
        hrConsultantArrayList.add(hrConsultant);
    }

    public ArrayList<HRHoD> getHrHoDArrayList() {
        return hrHoDArrayList;
    }

    public ArrayList<HRConsultant> getHrConsultantArrayList() {
        return hrConsultantArrayList;
    }

    public ArrayList<HRAssistant> getHrAssistantArrayList() {
        return hrAssistantArrayList;
    }
}
