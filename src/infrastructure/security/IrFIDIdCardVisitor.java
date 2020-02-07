package infrastructure.security;

import human_Resource.Person;
import infrastructure.Permission;

import java.util.ArrayList;
import java.util.Date;

public interface IrFIDIdCardVisitor {
    String getId();
    Date getValidFrom();
    Date getValidUntil();
    int[][] getIrisStructure();
    ArrayList<Permission> getPermissionList();
    boolean isLocked();
    Chip getChip();
    Person getPerson();

}
