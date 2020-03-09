import human_Resource.ResearchGroup;
import human_Resource.SecurityOfficer;
import human_Resource.Visitor;
import infrastructure.ControlCenter;
import infrastructure.Employee_Managment;
import infrastructure.Reception;
import infrastructure.Workplace;
import infrastructure.lhc.*;
import infrastructure.security.CardDevice.*;
import infrastructure.security.IDCard;
import infrastructure.security.IIDCard;
import infrastructure.security.IRoIDCardEmployee;
import infrastructure.security.SecurityCenter;
import main.DedectoreSearchAlgo;
import main.IdPerson;
import main.IrisGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ApplicationTest1 {
    Building building;
    LargeHadronCollider largeHadronCollider;
    Ring ring;
    Detector detector;
    ResearchGroup researchGroup;

    @BeforeEach
    void setUp() {
        building = new Building();
        largeHadronCollider = new LargeHadronCollider();
        building.setLargeHadronCollider(largeHadronCollider);
        largeHadronCollider.setBuilding(building);
        ring = new Ring();
        largeHadronCollider.setRing(ring);
        ring.setLargeHadronCollider(largeHadronCollider);
        detector = new Detector();
        detector.setRing(ring);
        ring.setDetector(detector);
        researchGroup = new ResearchGroup();

    }

    @AfterEach
    void tearDown() {

    }

    //Dem LargeHadronCollider ist ein Ring und zwei USP zugeordnet.
    @Order(1)
    @Test
    void test1(){
        assertNotNull(largeHadronCollider.getRing());
        assertNotNull(largeHadronCollider.getUsp01());
        assertNotNull(largeHadronCollider.getUsp02());
    }

    // Jede USP verfügt über 25 Batterien.
    @Order(2)
    @Test
    void test2(){
        assertEquals(25, largeHadronCollider.getUsp01().getBatteryArrayList().size());
        assertEquals(25, largeHadronCollider.getUsp02().getBatteryArrayList().size());
    }

    //Die ID-Karte für einen Besucher wird von der Reception korrekt erstellt.
    @Order(3)
    @Test
    void test3(){
        //CardDevice anlegen für Reception also den Writer
        IWriter writerReception = new Writer();
        ITouchpad touchpadReceptionReception = new Touchpad();
        IIrisScaenner iIrisScaennerReception = new IrisScaenner();
        writerReception.setIrisScaenner(iIrisScaennerReception);
        writerReception.setTouchpad(touchpadReceptionReception);
        Reception.instance.setWriter(writerReception);


        Employee_Managment.instance.createEmployee("Mueller","ReceptionWorker",researchGroup);
        Visitor visitor1 = new Visitor(IdPerson.instance.getIdPerson(),"Mustermann", IrisGenerator.instance.irisCreate());

        //Aufgabe erstelle ein ID-Karte für den Besucher
        visitor1.anmeldungRecptionIDCardErstellen(visitor1,"test");

        assertEquals("Mustermann",visitor1.getIdCard().getPerson().getName());
        assertNotNull(visitor1.getIdCard().getId());
        assertEquals(visitor1.getIris(),visitor1.getIdCard().getIrisStructure());
        assertNotNull(visitor1.getIdCard().getValidFrom());
        assertNotNull(visitor1.getIdCard().getValidUntil());
    }

    // Die ID-Karte für einen Mitarbeiter wird von dem Security Centre korrekt erstellt.
    @Order(4)
    @Test
    void test4(){
        IWriterEmployee writerEmployee = new Writer();
        IIrisScaenner iIrisScaennerSecurity = new IrisScaenner();
        IFingerAbScaenner fingerAbScaennerSec = new FinerAbScaenner();
        writerEmployee.setIrisScaenner(iIrisScaennerSecurity);
        writerEmployee.setFingerAbScaenner(fingerAbScaennerSec);
        SecurityCenter.instance.setWriter(writerEmployee);

        // ID Card der Employees erstellen
        Employee_Managment.instance.createEmployee("Hans","SecurityOfficer",researchGroup);
        int test= SecurityCenter.instance.getSecurityOfficerArrayList().get(0).getId();
        //ID Card wird erstellt
        SecurityCenter.instance.getSecurityOfficerArrayList().get(0).idCardEmployeeErstellen(test);
        SecurityOfficer securityOfficer = SecurityCenter.instance.getSecurityOfficerArrayList().get(0);

        IRoIDCardEmployee idCard = SecurityCenter.instance.getSecurityOfficerArrayList().get(0).getIdCard();
        assertEquals("Hans",idCard.getPerson().getName());
        assertEquals(securityOfficer.getIris(),idCard.getIrisStructure());
        assertNotNull(idCard.getValidFrom());
        assertNotNull(idCard.getValidUntil());


    }

    // .Nach dreimaliger Eingabe eines inkorrekten Passworts wird die ID-Karte gesperrt.
    @Order(5)
    @Test
    void test5(){
        // dies stand so nicht in der Aufagen und wurde deshalb nicht implimentiert
    }


    // 6.Eine gesperrte ID-Karte wird vom Reader abgewiesen.
    @Order(6)
    @Test
    void test6(){
        IWriterEmployee writerEmployee = new Writer();
        IIrisScaenner iIrisScaennerSecurity = new IrisScaenner();
        IFingerAbScaenner fingerAbScaennerSec = new FinerAbScaenner();
        writerEmployee.setIrisScaenner(iIrisScaennerSecurity);
        writerEmployee.setFingerAbScaenner(fingerAbScaennerSec);
        SecurityCenter.instance.setWriter(writerEmployee);
        IReader reader01 = new Reader();
        IIrisScaenner irsScanReader01= new IrisScaenner();
        IFingerAbScaenner fingerSaebReder01 = new FinerAbScaenner();
        ITouchpad touchpadReader01 = new Touchpad();
        reader01.setIrisScaenner(irsScanReader01);
        reader01.setFingerAbScaenner(fingerSaebReder01);
        reader01.setTouchpad(touchpadReader01);

        // ID Card der Employees erstellen
        Employee_Managment.instance.createEmployee("Hans","SecurityOfficer",researchGroup);
        int test= SecurityCenter.instance.getSecurityOfficerArrayList().get(0).getId();
        //ID Card wird erstellt
        SecurityCenter.instance.getSecurityOfficerArrayList().get(0).idCardEmployeeErstellen(test);
        SecurityOfficer securityOfficer = SecurityCenter.instance.getSecurityOfficerArrayList().get(0);

        IRoIDCardEmployee idCard = SecurityCenter.instance.getSecurityOfficerArrayList().get(0).getIdCard();
        //Security Center sperrt eine ID-Karte
        SecurityCenter.instance.getSecurityOfficerArrayList().get(0).lookIDCard(1);
        reader01.insterIDCardEmpl(idCard);
        assertEquals(true,idCard.isLocked());

    }

    //7.Eine abgelaufene ID-Karte wird vom Reader abgewiesen.
    @Order(7)
    @Test
    void test7(){
        IWriterEmployee writerEmployee = new Writer();
        IIrisScaenner iIrisScaennerSecurity = new IrisScaenner();
        IFingerAbScaenner fingerAbScaennerSec = new FinerAbScaenner();
        writerEmployee.setIrisScaenner(iIrisScaennerSecurity);
        writerEmployee.setFingerAbScaenner(fingerAbScaennerSec);
        SecurityCenter.instance.setWriter(writerEmployee);
        Reader reader01 = new Reader();
        IIrisScaenner irsScanReader01= new IrisScaenner();
        IFingerAbScaenner fingerSaebReder01 = new FinerAbScaenner();
        ITouchpad touchpadReader01 = new Touchpad();
        reader01.setIrisScaenner(irsScanReader01);
        reader01.setFingerAbScaenner(fingerSaebReder01);
        reader01.setTouchpad(touchpadReader01);
        // ID Card der Employees erstellen
        Employee_Managment.instance.createEmployee("Hans","SecurityOfficer",researchGroup);
        int test= SecurityCenter.instance.getSecurityOfficerArrayList().get(0).getId();
        //ID Card wird erstellt
        SecurityCenter.instance.getSecurityOfficerArrayList().get(0).idCardEmployeeErstellen(test);
        SecurityOfficer securityOfficer = SecurityCenter.instance.getSecurityOfficerArrayList().get(0);

        IRoIDCardEmployee idCard = SecurityCenter.instance.getSecurityOfficerArrayList().get(0).getIdCard();
        //Security Center sperrt eine ID-Karte
        SecurityCenter.instance.getSecurityOfficerArrayList().get(0).lookIDCard(1);
        reader01.insterIDCardEmpl(idCard);
        assertEquals(true, reader01.checkValidCard(idCard.getValidFrom(),idCard.getValidUntil()));
    }

    //8.Das ControlCenter hat drei Arbeitsplätze.Jeder Arbeitsplatz ist mit einer ResearchGroup – vertreten durch mindestenseinen Researcher - besetzt
    @Order(8)
    @Test
    void test8(){
        Workplace workplace0 = new Workplace();
        Workplace workplace1 = new Workplace();
        Workplace workplace2 = new Workplace();
        ControlCenter.instance.addWorkplce(workplace0);
        ControlCenter.instance.addWorkplce(workplace1);
        ControlCenter.instance.addWorkplce(workplace2);
        researchGroup.addWorkplace(workplace0);
        researchGroup.addWorkplace(workplace1);
        researchGroup.addWorkplace(workplace2);

        assertEquals(3,ControlCenter.instance.getWorkplaceArrayList().size());
        assertEquals(3,researchGroup.getWorkplaceArrayList().size());

    }

    //Dem Detector stehen drei Komponenten mit den Suchalgorithmen Native,BoyerMoore und KnuthMorrisPratt zur Verfügung.
    @Order(9)
    @Test
    void test9(){
        assertEquals("nativ", DedectoreSearchAlgo.nativ.toString());
        assertEquals("boyerMoore", DedectoreSearchAlgo.boyerMoore.toString());
        assertEquals("knurthMorrisPratt", DedectoreSearchAlgo.knurthMorrisPratt.toString());

    }

    @Order(10)
    @Test
    void test10(){

    }


    @Order(11)
    @Test
    void test11(){
        // erstellen der Protonen (Objekte der Klasse Proton
        String pfadOrdnerProtonen = "data/";
        building.getLargeHadronCollider().getRing().getProtonTrap01().loadData(pfadOrdnerProtonen);
        building.getLargeHadronCollider().getRing().getProtonTrap02().loadData(pfadOrdnerProtonen);
        assertEquals(25,building.getLargeHadronCollider().getRing().getProtonTrap01().getProtonQueue().size());
        assertEquals(25,building.getLargeHadronCollider().getRing().getProtonTrap02().getProtonQueue().size());
        int x = building.getLargeHadronCollider().getRing().getProtonTrap01().getProtonQueue().size();
        for (int i = 0; i < x; i++) {
            Proton proton= building.getLargeHadronCollider().getRing().getProtonTrap01().getProtonQueue().remove();
            for (int j = 0; j <100 ; j++) {
                for (int k = 0; k <100 ; k++) {
                    for (int l = 0; l <100 ; l++) {
                        assertNotNull(proton.getStructure()[j][k][l]);
                    }
                }
            }
        }

        int y = building.getLargeHadronCollider().getRing().getProtonTrap02().getProtonQueue().size();
        for (int i = 0; i < y; i++) {
            Proton proton= building.getLargeHadronCollider().getRing().getProtonTrap02().getProtonQueue().remove();
            for (int j = 0; j <100 ; j++) {
                for (int k = 0; k <100 ; k++) {
                    for (int l = 0; l <100 ; l++) {
                        assertNotNull(proton.getStructure()[j][k][l]);
                    }
                }
            }
        }


    }









}
