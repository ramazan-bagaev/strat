package Foundation.Needs;

import java.util.ArrayList;

public class Needs {

    private ArrayList<Need> listOfNeeds;

    private ArrayList<Need> listOfFulfilment;

    public Needs(){
        listOfNeeds = new ArrayList<>();
        listOfFulfilment = new ArrayList<>();
    }

    public void addNeed(Need need){
        listOfNeeds.add(need);
    }

    public void fulfilNeed(Need fulfilment){
        for(Need need: listOfNeeds){
            if (need.isSameNeedAs(fulfilment)){
                addFulfilment(fulfilment, need);
                return;
            }
        }
        return;
    }


    private void addFulfilment(Need fulfilment, Need needForFulfilment){
        for(Need need: listOfFulfilment){
            if (need.isSameNeedAs(fulfilment)){
                if (needForFulfilment.getAmount() > need.getAmount()){
                    need.addAmount(fulfilment.getAmount());
                    fulfilment.decreaseAmount(fulfilment.getAmount());
                }
                return;
            }
        }
        listOfFulfilment.add(fulfilment);
    }




}
