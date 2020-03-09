package infrastructure.security.CardDevice;

import human_Resource.Employee;
import human_Resource.Person;
import infrastructure.IDCard_Managment;
import infrastructure.Permission;
import infrastructure.security.*;
import main.AES;
import main.Configuration;
import main.IdPerson;

import java.awt.desktop.AboutEvent;
import java.util.ArrayList;
import java.util.Date;

public class Reader extends CardDevice implements IReader {
    private String id;
    private Date validFrom;
    private Date validUntil;
    private int[][] irisStructure = new int[10][10];
    private ArrayList<Permission> permissionList;
    private boolean isLocked;
    private ChipFingerAb chipFingerAb;
    private Person person;

    private String currentEmpoyeeIris;



    // noch ändern das die beiden Methde gleich heißen

    @Override
    public void insertIDCardVistor(IIDCard idCard) {
        person= idCard.getPerson();
        id = idCard.getId();
        validFrom = idCard.getValidFrom();
        validUntil = idCard.getValidUntil();
        irisStructure = idCard.getIrisStructure();
        permissionList = idCard.getPermissionList();
        isLocked = idCard.isLocked();

        if(checkValidCard(validFrom, validUntil)&& (!isLocked) && checkPassword(idCard.getChip())){
            System.out.println("Karte ist für den Besucher gültig");
        }
        else{
            System.out.println("Karte ist für den Besucher ungültig!!!!!!!!!!");
        }




    }

    @Override
    public void insterIDCardEmpl(IRoIDCardEmployee idCard) {
        person= idCard.getPerson();
        id = idCard.getId();
        validFrom = idCard.getValidFrom();
        validUntil = idCard.getValidUntil();
        irisStructure = idCard.getIrisStructure();
        permissionList = idCard.getPermissionList();
        isLocked = idCard.isLocked();
        chipFingerAb = idCard.getChipFingerAb();
        if(checkValidCard(validFrom, validUntil) &&   (!isLocked) ){
            System.out.println("Karte ist für den MA gültig");
        }
        else{
            System.out.println("Karte ist für den MA ungültig!!!!!!!!!!");
        }


    }

    @Override
    public void rFIDiDCardVistor(IrFIDIdCardVisitor idCard) {
        person= idCard.getPerson();
        id = idCard.getId();
        validFrom = idCard.getValidFrom();
        validUntil = idCard.getValidUntil();
        irisStructure = idCard.getIrisStructure();
        permissionList = idCard.getPermissionList();
        isLocked = idCard.isLocked();
        if(checkValidCard(validFrom, validUntil)&& (!isLocked)){
            System.out.println("Karte ist für den Besucher gültig");
        } else{
            System.out.println("Karte ist für den Besucher ungültig!!!!!!!!!!");
        }


    }

    @Override
    public void rFIDidCardEmployee(IrfiDIdCardEmpoyee idCard) {
        person= idCard.getPerson();
        id = idCard.getId();
        validFrom = idCard.getValidFrom();
        validUntil = idCard.getValidUntil();
        irisStructure = idCard.getIrisStructure();
        permissionList = idCard.getPermissionList();
        isLocked = idCard.isLocked();
        chipFingerAb = idCard.getChipFingerAb();
        if(checkValidCard(validFrom, validUntil)&& (!isLocked)&&  checkPassword(idCard.getChip())){
            System.out.println("Karte ist für den Besucher gültig");
        } else{
            System.out.println("Karte ist für den Besucher ungültig!!!!!!!!!!");
        }

    }

    @Override
    public void removeIDCard() {
        person = null;
        id = null;
        validFrom = null;
        validUntil = null;
        irisStructure = null;
        permissionList = null;
        isLocked = true;
        chipFingerAb = null;
    }


    public boolean checkValidCard(Date validFrom, Date validUntil){
        Date now = new Date();
        if (now.compareTo(validFrom) >0 && now.compareTo(validUntil)<0){
            return true;
        }
        else{
            return false;
        }

    }

    private boolean checkIris(int[][] irisStructureIDCard){
        String irisGescaennt = irisScaenne();
        String irisIDCard = irisInString(irisStructureIDCard);
        if (irisGescaennt.equals(irisIDCard)){
            return true;
        }
        else{
            return false;
        }


    }

    private String irisScaenne() {
        int[][] iris= new int[10][10];
        iris = getIrisScaenner().irisScann(person);
        String irisString = null;
        for (int i = 0; i <iris.length ; i++) {
            for (int j = 0; j < iris[i].length ; j++) {
               irisString = irisString + Integer.toString(iris[i][j]) ;
            }
        }
        currentEmpoyeeIris= irisString;

        return  irisString;
    }

    private String irisInString(int[][] iris){
        String irisString = null;
        for (int i = 0; i <iris.length ; i++) {
            for (int j = 0; j < iris[i].length ; j++) {
                irisString = irisString + Integer.toString(iris[i][j]) ;
            }
        }

        return irisString;

    }

    private boolean checkPassword(Chip chip){
        String passwordChipEnt = AES.decrypt(chip.getPassword(),Configuration.instance.getAesKey());
        String eingabe=touchpad.passwortEingabe();

        if (passwordChipEnt.equals(eingabe)){
            if (passwordChipEnt.equals("helloLHC2020")){
                System.out.println("Bitte ändern Sie das Initial-Passwort");
                String neuesPW = touchpad.passwortEingabe();
                String neuesPWVer = AES.encrypt(neuesPW,Configuration.instance.getAesKey());
                chip.setPassword(neuesPW);
            }

            return true;

        }else{
            return false;



        }


    }


}
