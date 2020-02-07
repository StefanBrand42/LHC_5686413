package infrastructure.security.CardDevice;

import human_Resource.Person;
import infrastructure.Permission;
import infrastructure.security.IDCard;
import infrastructure.security.IIDCard;

public interface IWriter  extends  ICardDevice{
    int[][] scanIris(Person person);
    void setValidFrom(String validFrom);
    void setValidUntil(String validUntil);
    void setIris(int[][] iris);
    void addPermission(Permission permission);
    void setSetIsLocked(boolean isLocked);
    void setChipPasswort(String passwort);

    void  insertIDCard(IIDCard idCard);



}
