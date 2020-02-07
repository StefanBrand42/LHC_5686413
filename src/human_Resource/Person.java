package human_Resource;

import infrastructure.security.IDCard;
import infrastructure.security.IIDCard;
import infrastructure.security.Passport;

public abstract class Person {
    protected int id;
    protected  String name;
    protected int[][] iris= new int[10][10];

    // fuer eins zu ein Beziehunh
    protected Passport passport;


    public Person(int id, String name, int[][] iris) {
        this.id = id;
        this.name = name;
        this.iris = iris;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }



    public int[][] getIris() {
        return iris;
    }

    public int getId() {
        return id;
    }
}
