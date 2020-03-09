package human_Resource;

import infrastructure.Workplace;

import java.util.ArrayList;

public class ResearchGroup {

    // fuer ein zu eins Beziehung
    private ArrayList<Workplace> workplaceArrayList;

    // fuer association
    private ArrayList<Researcher> researcherArrayList;
    private ArrayList<ScientificAssistant>scientificAssistantArrayList;


    public ResearchGroup() {
        researcherArrayList = new ArrayList<>();
        scientificAssistantArrayList = new ArrayList<>();
        workplaceArrayList = new ArrayList<>();

//        for (int i = 0; i <numberOfResearcher ; i++) {
//            researcherArrayList.add(new Researcher());
//        }
//        for (int i = 0; i <numberOfScientificAssistant ; i++) {
//            scientificAssistants.add(new ScientificAssistant());
//        }
    }

    public  void addResearcher(Researcher researcher){
        researcherArrayList.add(researcher);
    }

    public void addScientificAssistant(ScientificAssistant scientificAssistant){
        scientificAssistantArrayList.add(scientificAssistant);
    }

    public void addWorkplace(Workplace workplace){
        workplaceArrayList.add(workplace);
    }

    public ArrayList<Researcher> getResearcherArrayList() {
        return researcherArrayList;
    }

    public ArrayList<Workplace> getWorkplaceArrayList() {
        return workplaceArrayList;
    }
}
