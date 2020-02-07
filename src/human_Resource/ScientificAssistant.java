package human_Resource;

import human_Resource.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScientificAssistant extends Employee {
    private Date periodFrom;
    private Date periodUntil;



    public ScientificAssistant(int id, String name, int[][] iris, String fingerAb, boolean isManager, boolean isMentor, boolean hasBugetResponsibiltiy, String periodFrom, String periodUntil) {
        super(id, name, iris, fingerAb, isManager, isMentor, hasBugetResponsibiltiy);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyy");
        try {
            this.periodFrom = simpleDateFormat.parse(periodFrom);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Fehler beim datum Eingeben");
        }
        try {
            this.periodUntil = simpleDateFormat.parse(periodUntil);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Fehler beim datum Eingeben");
        }


    }
}
