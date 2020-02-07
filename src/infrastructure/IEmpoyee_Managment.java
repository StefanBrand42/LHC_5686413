package infrastructure;

import human_Resource.Employee;

import java.util.Map;

public interface IEmpoyee_Managment extends IROEmployye_Managment {
    void setEmployeeMap(Map<Integer, Employee> employeeMap);
}
