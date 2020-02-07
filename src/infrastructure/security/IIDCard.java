package infrastructure.security;

import human_Resource.Person;
import infrastructure.Permission;

import java.text.ParseException;

public interface IIDCard extends IROIDCard {
    void setPerson(Person person);
    void setValidFrom(String validFrom) throws ParseException;
    void setValidUntil(String validUntil) throws ParseException;
    void setIrisStructure(int[][] irisStructure);
    void addPermissionList(Permission permission);



}
