package infrastructure.lhc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Experiment implements IExperiment {

    private  final UUID uuid;
    private final String dateTimeStamp;
    private  boolean isHiggsBosonFound;

    private final int idProton1;
    private final int idProton2;


    // fuer die Composition
    private ArrayList<Block> blockArrayList;

    // für aus der Datenbank erstellen
    public Experiment(UUID uuid, String dateTimeStamp, boolean isHiggsBosonFound, int idProton1, int idProton2, ArrayList<Block> blockArrayList) {
        this.uuid = uuid;
        this.dateTimeStamp = dateTimeStamp;
        this.isHiggsBosonFound = isHiggsBosonFound;
        this.idProton1 = idProton1;
        this.idProton2 = idProton2;
        this.blockArrayList = blockArrayList;
    }

    public Experiment(Proton proton01, Proton proton02) {
        uuid = UUID.randomUUID();
        idProton1 = proton01.getId();
        idProton2 = proton02.getId();
        blockArrayList = new ArrayList<>();
        //String proton1String = proton01.getProton1Mio();
        //String proton2String = proton02.getProton1Mio();
        char[][][] proton1Structure = proton01.getStructure();
        char[][][] proton2Structure = proton02.getStructure();
        String proton1Split = "";
        String proton2Split = "";
        String blockSting;
        Date jetzt = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        dateTimeStamp = simpleDateFormat.format(jetzt);
        int d5 =0;
        for (int j = 0; j <100 ; j++) {
            for (int k = 0; k <100 ; k++) {
                for (int l = 0; l <100 ; l++) {
                    proton1Split = proton1Split+ proton1Structure[j][k][l];
                    proton2Split = proton2Split+ proton2Structure[j][k][l];
                    d5++;
                    if (d5==5){
                        blockSting = proton1Split+proton2Split;
                        blockArrayList.add(new Block(blockSting));
                        d5 = 0;
                        blockSting = "";
                        proton1Split="";
                        proton2Split="";

                    }


                }
            }
        }

//        for (int i = 0; i < 1000000 ; i=i+5) {
//           proton1Split = proton1String.substring(i,i+5);
//           proton2Split = proton2String.substring(i,i+5);
//           blockSting= proton1Split+proton2Split;
//           blockArrayList.add(new Block(blockSting));
//        }
    }


    public UUID getUuid() {
        return uuid;
    }

    public String getDateTimeStamp() {
        return dateTimeStamp;
    }

    public boolean isHiggsBosonFound() {
        return isHiggsBosonFound;
    }

    public int getIdProton1() {
        return idProton1;
    }

    public int getIdProton2() {
        return idProton2;
    }

    public ArrayList<Block> getBlockArrayList() {
        return blockArrayList;
    }



    public void setHiggsBosonFound(boolean higgsBosonFound) {
        isHiggsBosonFound = higgsBosonFound;
    }



}
