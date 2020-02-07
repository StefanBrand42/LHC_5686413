package main;

public enum IdPerson {
    instance;
    int idPerson = 0;

    public int getIdPerson(){
        idPerson++;
        return idPerson;
    }
}
