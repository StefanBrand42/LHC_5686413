package infrastructure.security;

import human_Resource.Person;
import infrastructure.Permission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IDCard implements IIDCard , IrFIDIdCardVisitor{
    private final String id;
    protected Date validFrom;
    protected Date validUntil;
    protected int[][] irisStructure = new int[10][10];
    protected ArrayList<Permission> permissionList;
    protected boolean isLocked;

    // fuer eins zu eins Beziehung
    private Person person;

    // fuer Composition
    private Chip chip;

    public IDCard(String id) {
        this.id = id;
        chip = new Chip();
        permissionList = new ArrayList<>();
    }



    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.validFrom = simpleDateFormat.parse(validFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.validUntil = simpleDateFormat.parse(validUntil);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int[][] getIrisStructure() {
        return irisStructure;
    }

    public void setIrisStructure(int[][] irisStructure) {
        this.irisStructure = irisStructure;
    }

    public ArrayList<Permission> getPermissionList() {
        return permissionList;
    }

    public void addPermissionList(Permission permission) {
        permissionList.add(permission);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setisLocked(boolean islocked) {
        this.isLocked = islocked;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    public Chip getChip() {
        return chip;
    }

    public String getId() {
        return id;
    }



}
