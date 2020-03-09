package main;

import java.io.FileInputStream;
import java.util.Properties;

public enum Configuration {
    instance;

    boolean loadFromDatabase = true;


    String aesKey = "x7z99kvb6lU";

//    Configuration() {
//        AES.setKey(aesKey);
//    }

    public String getAesKey() {
        return aesKey;
    }

    //Komponette für den Suchalgo
    //hier den Typ einstellen
    public DedectoreSearchAlgo searchAlgo = DedectoreSearchAlgo.nativ;
    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String pathToJar = userDirectory + fileSeparator + searchAlgo + fileSeparator + "jar" + fileSeparator + "SearchAlgo.jar";


}
