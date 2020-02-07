package infrastructure;

import human_Resource.Employee;
import infrastructure.security.IDCard;
import infrastructure.security.CardDevice.Reader;
import infrastructure.security.IIDCard;

import java.util.HashMap;
import java.util.Map;

public enum IDCard_Managment{
    instance;


    private Map<Integer, IDCard> idCardHashMap;



    IDCard_Managment() {

        idCardHashMap = new HashMap<>();
    }

    public void addIDCard(IDCard idCard){
        idCardHashMap.put(idCard.getPerson().getId(), idCard);
    }


    public  void  assignIDCard(IDCard idCard, Employee employee){

    }

    public void lookIDCard(IDCard idCard){

    }

    public  void  clearIDCard(IDCard idCard){

    }

    public Map<Integer, IDCard> getIdCardHashMap() {
        return idCardHashMap;
    }
}
