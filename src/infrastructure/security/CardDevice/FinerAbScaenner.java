package infrastructure.security.CardDevice;

import human_Resource.Employee;
import human_Resource.Person;

public class FinerAbScaenner implements IFingerAbScaenner {

    @Override
    public String fingerAbScann(Employee employee) {
        return employee.getFingerAb();
    }
}
