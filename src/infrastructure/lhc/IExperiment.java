package infrastructure.lhc;

import java.util.ArrayList;
import java.util.UUID;

public interface IExperiment {
    UUID getUuid();
    String getDateTimeStamp();
    boolean isHiggsBosonFound();
    int getIdProton1();
    int getIdProton2();
    ArrayList<Block> getBlockArrayList();
    void setHiggsBosonFound(boolean higgsBosonFound);


}
