package infrastructure.lhc;

import infrastructure.ProtonTrapID;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ProtonTrap {

    private ProtonTrapID id;

    // fuer ein zu 100 beziehung

    //private ArrayList<Proton>protonArrayList;
    //private Stack<Proton> prontonStack;
    private Queue<Proton>protonQueue;

    public ProtonTrap() {
        //protonArrayList = new ArrayList<>();
        //prontonStack = new Stack<>();
        protonQueue = new LinkedList<>();

    }

    public void loadData(String dataFilePath){
        BufferedReader br = null;
        String proton_FilePath;
        String line;

        int x =0;
        int d =0;
        if (id == ProtonTrapID.A){
            x=1;
        }else if(id == ProtonTrapID.B){
            x=2;
        }
        if (x==1 || x==2){
            for (int i = x; i <=50 ; i= i+2) {
                if (i<10){
                    proton_FilePath  = dataFilePath +"proton_0"+i+".txt";
                }else {
                    proton_FilePath  = dataFilePath +"proton_"+i+".txt";
                }
                try {
                    br = new BufferedReader(new FileReader(new File(proton_FilePath)));
                    d = 0;
                    char protonStructure[][][] = new char[100][100][100];
                    try {
                        line = br.readLine();
                        for (int j = 0; j <100 ; j++) {
                            for (int k = 0; k <100 ; k++) {
                                for (int l = 0; l <100 ; l++) {
                                    protonStructure[j][k][l] = line.charAt(d);
                                    d++;
                                }
                            }
                        }

                        // neu Proton erstellen
                        Proton test = new Proton(i,protonStructure);
                        char[][][] testStr = test.getStructure();
                        protonQueue.add(test);
                        //prontonStack.push(new Proton(i,protonStructure));
                        //protonArrayList.add(new Proton(i,line));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }




    }

    public void release(){

    }

    public Queue<Proton> getProtonQueue() {
        return protonQueue;
    }

    public void setId(ProtonTrapID id) {
        this.id = id;
    }
}
