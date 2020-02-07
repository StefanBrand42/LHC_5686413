package infrastructure.security.CardDevice;


import human_Resource.Person;

public class IrisScaenner implements IIrisScaenner {


    @Override
    public int[][] irisScann(Person person) {
        return person.getIris();
    }
}
