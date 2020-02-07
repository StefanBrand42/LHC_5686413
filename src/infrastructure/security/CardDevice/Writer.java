package infrastructure.security.CardDevice;

import human_Resource.Person;
import infrastructure.Permission;
import infrastructure.security.IDCard;
import infrastructure.security.IDCardEmployee;
import infrastructure.security.IIDCard;
import infrastructure.security.IRoIDCardEmployee;

import java.text.ParseException;
import java.util.ArrayList;

public class Writer extends CardDevice implements IWriterEmployee {


    private IIDCard idCard;

    public Writer() {
    }




    public  void  insertIDCard(IIDCard idCard){
        this.idCard =idCard;

    }

    public void removeIDCard(){
        idCard = null;
    }



    @Override
    public void setValidFrom(String validFrom) {
        try {
            idCard.setValidFrom(validFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setValidUntil(String validUntil) {
        try {
            idCard.setValidUntil(validUntil);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setIris(int[][] iris) {
        idCard.setIrisStructure(iris);
    }


    @Override
    public int[][] scanIris(Person person) {

        return irisScaenner.irisScann(person);
    }

    @Override
    public void addPermission(Permission permission) {
        idCard.addPermissionList(permission);

    }

    @Override
    public void setSetIsLocked(boolean isLocked) {
        idCard.setisLocked(isLocked);

    }

    @Override
    public void setChipPasswort(String passwort) {

        idCard.getChip().setPassword(passwort);
    }


    @Override
    public IRoIDCardEmployee erstelleIDCardEmployee(String id, String validFrom, String validUntil, int[][] isrisStructure, ArrayList<Permission> permissionArrayList, boolean isLocked, String password, String fingerAbrduck, Person person) {
        IRoIDCardEmployee idCardEmployee = new IDCardEmployee(id,  validFrom,  validUntil,  isrisStructure, permissionArrayList,  isLocked, password, fingerAbrduck, person);
        return idCardEmployee;
    }

    @Override
    public IFingerAbScaenner getFingerAbScaenner() {
        return fingerAbScaenner;
    }
}

