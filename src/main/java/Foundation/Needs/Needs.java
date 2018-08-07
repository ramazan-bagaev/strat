package Foundation.Needs;

import java.util.ArrayList;

public class Needs {

    private ArrayList<Need> listOfNeeds;

    private ArrayList<Need> listOfFullfilments;

    public Needs(){
        listOfNeeds = new ArrayList<>();
        listOfFullfilments = new ArrayList<>();
    }

    public void addNeed(Need need){
        listOfNeeds.add(need);
    }

    public void fullfilNeed(Need need){

    }




}
