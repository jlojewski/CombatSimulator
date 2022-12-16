package Z1;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class Quip {
    public ArrayList<String> quipList;
    public ArrayList<String> thoughtList;

    private static Quip quipInstance;

    private Quip() {
    }

    public static Quip getInstance() {
        if (quipInstance == null) {
            quipInstance = new Quip();
        }

        return quipInstance;
    }

    public ArrayList<String> getQuipList() {
        return quipList;
    }

    public ArrayList<String> getThoughtList() {
        return thoughtList;
    }

    public void setThoughtList(ArrayList<String> thoughtList) {
        this.thoughtList = thoughtList;
    }

    public void setQuipList(ArrayList<String> quipList) {
        this.quipList = quipList;
    }


    public void initializeQuipList() {
        setQuipList(IOManager.getInstance().importQuipList());
        setThoughtList(IOManager.getInstance().importThoughtList());

    }


    public String printQuip(ArrayList<String> quipListToUse) {
        int randomCommentary = ThreadLocalRandom.current().nextInt(1, (quipListToUse.size()+1));

        return quipListToUse.get(randomCommentary);
    }

    public String printThought(ArrayList<String> thoughtListToUse) {
        int randomCommentary = ThreadLocalRandom.current().nextInt(1, (thoughtListToUse.size()+1));

        return thoughtListToUse.get(randomCommentary);
    }


}
