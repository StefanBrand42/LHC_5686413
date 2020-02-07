package infrastructure;

import human_Resource.ReceptionWorker;
import infrastructure.security.IDCard;
import infrastructure.security.CardDevice.IWriter;
import infrastructure.security.IIDCard;
import main.ID_IDCard;

import java.util.ArrayList;
import java.util.Stack;

public enum Reception {
    instance;
    private Stack<IIDCard> idCardStack;
    private ArrayList<ReceptionWorker> receptionWorkerArrayList;
    private IWriter writer;


    Reception() {
        receptionWorkerArrayList = new ArrayList<>();
        idCardStack = new Stack<IIDCard>();
        for (int i = 0; i < 15 ; i++) {
            String id = ID_IDCard.instance.getID_IDCard() +"besucherCard";
            IIDCard idCard = new IDCard(id);
            idCardStack.push(idCard);
        }


    }
    public void addReceptionWorker(ReceptionWorker receptionWorker){

        receptionWorkerArrayList.add(receptionWorker);
    }

    public ArrayList<ReceptionWorker> getReceptionWorkerArrayList() {

        return receptionWorkerArrayList;
    }

    public Stack<IIDCard> getIdCardStack() {

        return idCardStack;
    }

    public void setWriter(IWriter writer)
    {
        this.writer = writer;
    }

    public IWriter getWriter() {
        return writer;
    }
}
