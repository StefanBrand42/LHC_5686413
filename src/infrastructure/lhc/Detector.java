package infrastructure.lhc;

import com.google.common.eventbus.Subscribe;
import infrastructure.lhc.event.AnalyseEvent;
import infrastructure.security.CardDevice.Reader;
import main.Configuration;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Detector extends Subscriber implements  IDector{
    private static final String  higgsBosonStructure = "higgs";
    private  boolean isAcctivated;
    private LinkedList<IExperiment> experimentList;
    private Reader redaer;

    private Date startAnalyse;


    // fuer ein zu ein Beziehung
    private Ring ring;


    // für Komponenten SuchAlgo
    private Object port;
    //Detector detector = new Detector();

    public Detector() {
        //detector = new Detector();
        experimentList = new LinkedList<>();
        // test
//       createSearchAlgoPortInstance();
//        try {
//            Method searchModul = port.getClass().getMethod("search",String.class, String.class);
//            int x = (int) searchModul.invoke(port,"ccHallo", "Hallo");
//            System.out.println("Gefunden---------------");
//            System.out.println(x);
//        } catch (NoSuchMethodException e) {
//            System.out.println("Methote nicht gefunden!!!!!!!!");
//            e.printStackTrace();
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        //detector.search();



        // So aufrufen Search
       // detector.search();



    }

    @Subscribe
    public void receive(AnalyseEvent analyseEvent){
        System.out.println("---------Start Analyse---------------- ");
        startAnalyse= new Date();
        for (int i = 0; i <experimentList.size() ; i++) {
            searche(experimentList.get(i));
        }

    }

    public void searche(IExperiment experiment){
        createSearchAlgoPortInstance();
        try {
            Method searchModul = port.getClass().getMethod("search",String.class, String.class);
            //System.out.println("Analyse Expermient von Proton:"+experiment.getIdProton1()+" mit Proton:"+experiment.getIdProton2());
            for (int i = 0; i <experiment.getBlockArrayList().size() ; i++) {
                int x = (int) searchModul.invoke(port,experiment.getBlockArrayList().get(i).getStructure(), higgsBosonStructure);
                if (x!=-1)
                {
                    Date jetzt = new Date();
                    long delt= jetzt.getTime()-startAnalyse.getTime();




                    experiment.setHiggsBosonFound(true);

                    System.out.println("higgs wurde gefunden bei der Kollision von Proton:"+experiment.getIdProton1()+" mit dem Proton"+ experiment.getIdProton2());
                    System.out.println("ID des Experimentes:"+experiment.getUuid());
                    System.out.println("Zeitstempel des Experiments:"+experiment.getDateTimeStamp());
                    System.out.println("ID des beteiligten Protonen: ID1:"+experiment.getIdProton1() +"   ID2:"+experiment.getIdProton2());
                    System.out.println("ID es Blockes: "+experiment.getBlockArrayList().get(i).getUuid());
                    System.out.println("Strukur des Blockes: "+experiment.getBlockArrayList().get(i).getStructure());
                    System.out.println("Laufzeit der Analyse in ms:"+delt);

                }

            }


        } catch (NoSuchMethodException e) {
            System.out.println("Methote nicht gefunden!!!!!!!!");
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void createSearchAlgoPortInstance() {
        Object instance;

        try {
            //System.out.println("pathToJar : " + Configuration.instance.pathToJar);
            URL[] urls = {new File(Configuration.instance.pathToJar).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, Detector.class.getClassLoader());
            Class clazz = Class.forName("SearchAlgor", true, urlClassLoader);
            //System.out.println("clazz     : " + clazz.toString());

            instance = clazz.getMethod("getInstance").invoke(null);
            port = clazz.getDeclaredField("port").get(instance);
            //System.out.println("port      : " + port.hashCode());

            Method getVersion = port.getClass().getMethod("getVersion");
            String version = (String) getVersion.invoke(port);
            //System.out.println("version   : " + version);
        } catch (Exception e) {
            System.out.println("--- exception");
            System.out.println(e.getMessage());
        }
    }



    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public String getHiggsBosonStructure() {
        return higgsBosonStructure;
    }

    public boolean isAcctivated() {
        return isAcctivated;
    }

    public LinkedList<IExperiment> getExperimentList() {
        return experimentList;
    }

    public Reader getRedaer() {
        return redaer;
    }

    @Override
    public void addExperiment(IExperiment experiment) {
        experimentList.add(experiment);
    }
}


