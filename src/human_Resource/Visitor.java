package human_Resource;

import human_Resource.Person;
import infrastructure.Reception;
import infrastructure.security.IIDCard;
import infrastructure.security.IROIDCard;

public class Visitor  extends Person {
    private IIDCard idCard;

    public Visitor(int id, String name, int[][] iris) {
        super(id, name, iris);

    }


    public void anmeldungRecptionIDCardErstellen(Visitor visitor, String passwort){
        Reception.instance.getReceptionWorkerArrayList().get(0).iDCardErstellen(visitor, passwort);

    }

    public void setIdCard(IIDCard idCard) {
        this.idCard = idCard;
    }

    public IIDCard getIdCard() {
        return idCard;
    }
}
