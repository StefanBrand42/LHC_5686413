package main;

public enum ID_IDCard {
    instance;
    int id_iDCard = 0;

    public int getID_IDCard(){
        id_iDCard++;
        return id_iDCard;
    }
}
