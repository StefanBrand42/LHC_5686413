package infrastructure.security.CardDevice;

import human_Resource.Person;
import infrastructure.Permission;
import infrastructure.security.IRoIDCardEmployee;

import java.util.ArrayList;
import java.util.Date;

public interface IWriterEmployee extends IWriter {
    IRoIDCardEmployee erstelleIDCardEmployee(String id, String validFrom, String validUntil, int[][] isrisStructure, ArrayList<Permission> permissionArrayList, boolean isLocked, String password, String fingerAbrduck, Person person);
}
