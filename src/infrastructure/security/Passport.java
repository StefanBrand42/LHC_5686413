package infrastructure.security;

import human_Resource.Person;

public class Passport {
    private String id;

    // fuer ein zu ein beziehung
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }
}
