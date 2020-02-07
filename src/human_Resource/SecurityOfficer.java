package human_Resource;

import human_Resource.Employee;
import infrastructure.Employee_Managment;
import infrastructure.IDCard_Managment;
import infrastructure.Permission;
import infrastructure.security.*;
import infrastructure.security.CardDevice.IFingerAbScaenner;
import main.*;

import java.security.MessageDigest;
import java.util.ArrayList;

public class SecurityOfficer extends Employee {
    private boolean hasWeapon;

    public SecurityOfficer(int id, String name, int[][] iris, String fingerAb, boolean isManager, boolean isMentor, boolean hasBugetResponsibiltiy, boolean hasWeapon) {
        super(id, name, iris, fingerAb, isManager, isMentor, hasBugetResponsibiltiy);
        this.hasWeapon = hasWeapon;
    }



    public void idCardEmployeeErstellen(int idEmployee){
        Employee employee = Employee_Managment.instance.getEmployeeMap().get(idEmployee);
        String namdeKlasse=employee.getClass().getName();
        ArrayList<Permission> permissionArrayList = new ArrayList<>();
        switch (namdeKlasse){
            case "human_Resource.Researcher":
                permissionArrayList.add(Permission.ControlCenter);
                permissionArrayList.add(Permission.Researcher);
                break;

            case "human_Resource.ScientificAssistant" :
                permissionArrayList.add(Permission.ControlCenter);
                permissionArrayList.add(Permission.Researcher);
                break;

            case "human_Resource.SecurityOfficer":
                permissionArrayList.add(Permission.Security);
                break;
        }


        String id = ID_IDCard.instance.getID_IDCard() +"employeeCard";
        int[][] iris = SecurityCenter.instance.getWriter().getIrisScaenner().irisScann(employee);
        String fingerAb = SecurityCenter.instance.getWriter().getFingerAbScaenner().fingerAbScann(employee);



        String test= Configuration.instance.getAesKey();
        String passwortVer = AES.encrypt("helloLHC2020",Configuration.instance.getAesKey());
        IRoIDCardEmployee erstelleIDCardEmployee = SecurityCenter.instance.getWriter().erstelleIDCardEmployee(id,"01.01.2020","31.12.2020", iris,permissionArrayList,false,passwortVer,fingerAb,employee);
        employee.setIdCard(erstelleIDCardEmployee);
        IDCard_Managment.instance.addIDCard((IDCard) erstelleIDCardEmployee);


    }

    public void lookIDCard(int idEmployee){
        IIDCard idCardEmployee =  IDCard_Managment.instance.getIdCardHashMap().get(idEmployee);
        SecurityCenter.instance.getWriter().insertIDCard(idCardEmployee);
        SecurityCenter.instance.getWriter().setSetIsLocked(true);
        SecurityCenter.instance.getWriter().removeIDCard();

    }


}
