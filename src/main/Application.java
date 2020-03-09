package main;

import human_Resource.*;
import human_Resource.HrDep.HRDepartment;
import infrastructure.*;
import infrastructure.lhc.*;
import infrastructure.security.CardDevice.*;
import infrastructure.security.SecurityCenter;
import persistenzlayer.PersistanceLayerDB;


public class Application {

    public static void main(String[] args) {


        // ein zu eins Verbidnunegn werden ueber set angelegt, eins zu mehreren legt der Constructor der einen Klasse an

        // LHC und Energy
        Building building = new Building();
        LargeHadronCollider largeHadronCollider = new LargeHadronCollider();
        building.setLargeHadronCollider(largeHadronCollider);
        largeHadronCollider.setBuilding(building);
        Ring ring = new Ring();
        ring.setLargeHadronCollider(largeHadronCollider);
        largeHadronCollider.setRing(ring);
        Detector detector = new Detector();
        detector.setRing(ring);
        ring.setDetector(detector);


        //CardDevice anlegen für Reception also den Writer
        IWriter writerReception = new Writer();
        ITouchpad touchpadReceptionReception = new Touchpad();
        IIrisScaenner iIrisScaennerReception = new IrisScaenner();
        writerReception.setIrisScaenner(iIrisScaennerReception);
        writerReception.setTouchpad(touchpadReceptionReception);
        Reception.instance.setWriter(writerReception);


        // Personen anlegen
        ResearchGroup researchGroup = new ResearchGroup();
        Employee_Managment.instance.createEmployee("Mueller","ReceptionWorker",researchGroup);
        Visitor visitor1 = new Visitor(IdPerson.instance.getIdPerson(),"Mustermann",IrisGenerator.instance.irisCreate());


        //Aufgabe erstelle ein ID-Karte für den Besucher
        visitor1.anmeldungRecptionIDCardErstellen(visitor1,"test");

        //Aufagbe erstellen MA IDCard
        IWriterEmployee writerEmployee = new Writer();
        IIrisScaenner iIrisScaennerSecurity = new IrisScaenner();
        IFingerAbScaenner fingerAbScaennerSec = new FinerAbScaenner();
        writerEmployee.setIrisScaenner(iIrisScaennerSecurity);
        writerEmployee.setFingerAbScaenner(fingerAbScaennerSec);
        SecurityCenter.instance.setWriter(writerEmployee);

        // ID Card der Employees erstellen
        Employee_Managment.instance.createEmployee("Hans","SecurityOfficer",researchGroup);
        int test= SecurityCenter.instance.getSecurityOfficerArrayList().get(0).getId();
        System.out.println(test);
        //ID Card wird erstellt
        SecurityCenter.instance.getSecurityOfficerArrayList().get(0).idCardEmployeeErstellen(test);
        System.out.println(SecurityCenter.instance.getSecurityOfficerArrayList().get(0).getIdCard().getValidUntil());
        System.out.println(Employee_Managment.instance.getEmployeeMap().get(1).getClass().getName());

        // karte Vistor prüfen

        //Reader 01 erzeugen
        IReader reader01 = new Reader();
        IIrisScaenner irsScanReader01= new IrisScaenner();
        IFingerAbScaenner fingerSaebReder01 = new FinerAbScaenner();
        ITouchpad touchpadReader01 = new Touchpad();
        reader01.setIrisScaenner(irsScanReader01);
        reader01.setFingerAbScaenner(fingerSaebReder01);
        reader01.setTouchpad(touchpadReader01);

        System.out.println(visitor1.getIdCard().getId());
        reader01.insertIDCardVistor(visitor1.getIdCard());

        // Test Hasmap Employee
        System.out.println("----------------------");
        System.out.println("Employee Map");
        System.out.println(Employee_Managment.instance.getEmployeeMap());
        System.out.println("ID Card Map");
        System.out.println(IDCard_Managment.instance.getIdCardHashMap());

        System.out.println("----------------------");

        //Karte Empoyee Prüfen
        reader01.insterIDCardEmpl(Employee_Managment.instance.getEmployeeMap().get(3).getIdCard());


        //Aufgabe Forscher greifen lesen auf die Daten des Dektetors
        // geht aktuell nicht anders da zwichen Person und Gebäude eine vernüfung ist (wie soll die Person wissen das es das Gebäude gibt????????????)
        System.out.println("Forsche lesen auf Dectetordaten");
        IRODetector detecorLesend = building.getLargeHadronCollider().getRing().getDetector();
        System.out.println(detecorLesend.getHiggsBosonStructure());


        //HRAssistent hat lesend Zugriff auf die daten der MA
        System.out.println("-----------HRAssistent hat lesend Zugriff auf die daten der MA-----------");
        Employee_Managment.instance.createEmployee("Jung","HRAssistant",researchGroup);
        System.out.println(HRDepartment.instance.getHrAssistantArrayList().get(0).getIroEmployye_managment().getEmployeeMap());

        //Security Center sperrt eine ID-Karte
        SecurityCenter.instance.getSecurityOfficerArrayList().get(0).lookIDCard(3);





        // Workpace anlegen
        // Conrolcenter ist ein enum
        Workplace workplace0 = new Workplace();
        Workplace workplace1 = new Workplace();
        Workplace workplace2 = new Workplace();
        ControlCenter.instance.addWorkplce(workplace0);
        ControlCenter.instance.addWorkplce(workplace1);
        ControlCenter.instance.addWorkplce(workplace2);
        researchGroup.addWorkplace(workplace0);
        researchGroup.addWorkplace(workplace1);
        researchGroup.addWorkplace(workplace2);

        // EventBus
        ControlCenter.instance.addSubsriber(ring);
        ControlCenter.instance.addSubsriber(detector);


        if (Configuration.instance.loadFromDatabase == false){
            // erstellen der Protonen (Objekte der Klasse Proton
            String pfadOrdnerProtonen = "data/";
            building.getLargeHadronCollider().getRing().getProtonTrap01().loadData(pfadOrdnerProtonen);
            building.getLargeHadronCollider().getRing().getProtonTrap02().loadData(pfadOrdnerProtonen);



            // Start Kollosion
            ControlCenter.instance.startExperiment(InitialEnergy.startEnergie50k);
            //ControlCenter.instance.startExperiment(InitialEnergy.startEnergie50k,ExperimentScope.ES5);
            
            // Experimente in DadabadeLaden
            // Zugriff Database
            PersistanceLayerDB.instance.setupConnection();
            // löschen Tabelle
            PersistanceLayerDB.instance.dropTableExperimentBlock();
            //Tabellen anlegen
            PersistanceLayerDB.instance.createTableExperiment();
            PersistanceLayerDB.instance.createTableBlock();

            // Daten rein schreiben
            int x = largeHadronCollider.getRing().getDetector().getExperimentList().size();
            System.out.println(x);
            for (int i = 0; i < largeHadronCollider.getRing().getDetector().getExperimentList().size() ; i++) {
                PersistanceLayerDB.instance.insert(largeHadronCollider.getRing().getDetector().getExperimentList().get(i));
            }

            
            
        }else {
            
        }



        // Start Analyse
        ControlCenter.instance.startAnalyse();









































        System.out.println("ALLES IST GUT!");






        //Employee_Managment.instance.
    }


}

