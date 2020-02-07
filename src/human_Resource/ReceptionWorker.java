package human_Resource;

import infrastructure.Employee_Managment;
import infrastructure.IDCard_Managment;
import infrastructure.Permission;
import infrastructure.Reception;
import infrastructure.security.IDCard;
import infrastructure.security.IIDCard;
import main.AES;
import main.Application;
import main.Configuration;
import main.ID_IDCard;

import java.text.SimpleDateFormat;


public class ReceptionWorker extends Employee {

    public ReceptionWorker(int id, String name, int[][] iris, String fingerAb, boolean isManager, boolean isMentor, boolean hasBugetResponsibiltiy) {
        super(id, name, iris, fingerAb, isManager, isMentor, hasBugetResponsibiltiy);
    }

    public void iDCardErstellen(Visitor visitor, String passwort){
        IIDCard idCard= Reception.instance.getIdCardStack().pop();
        Reception.instance.getWriter().insertIDCard(idCard);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyy");

        Reception.instance.getWriter().setValidFrom("01.01.2020");
        Reception.instance.getWriter().setValidUntil("31.12.2020");
        //Iris
        int[][] iriInt =Reception.instance.getWriter().scanIris(visitor);
        Reception.instance.getWriter().setIris(iriInt);

        Reception.instance.getWriter().addPermission(Permission.Visitor);
        Reception.instance.getWriter().setSetIsLocked(false);

        //PasswortEingabe
        //Passwort verschluesseln
        String test= Configuration.instance.getAesKey();
        String passwortVer = AES.encrypt(passwort,Configuration.instance.getAesKey());

        Reception.instance.getWriter().setChipPasswort(passwortVer);
        //Card mit Visitor verknüfen
        idCard.setPerson(visitor);
        visitor.setIdCard(idCard);
        // Karte im IDcardManagment ablegen
        IDCard_Managment.instance.addIDCard((IDCard) idCard);
        //Karte entnehmen
        Reception.instance.getWriter().removeIDCard();









    }

    public void iDCardUebergeben(){

    }


}
