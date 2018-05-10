package Foundation.Fauna;

import Utils.Broadcaster;
import Foundation.Recources.Resource;

import java.util.ArrayList;

public abstract class Animal implements Broadcaster{

    private int consumtionStage; // from 1 to 2
    protected int amount;
    private int standartAmount;
    protected int animalId;
    protected String name;

    public Animal(int consumtionStage, int amount){
        this.consumtionStage = consumtionStage;
        this.amount = amount;
        this.standartAmount = amount;
    }

    public int getConsumtionStage(){
        return consumtionStage;
    }

    public int getAmount(){
        return amount;
    }

    public int getAnimalId(){
        return animalId;
    }

    public void run(){
        amount = standartAmount;
    }

    public abstract ArrayList<Resource> getResources(int amount);
    public abstract boolean isHuntable();

    @Override
    public String getValue(String key) {
        switch (key){
            case "fullInfo":
                String conStage = "";
                if (consumtionStage == 1) conStage = "herb";
                if (consumtionStage == 2) conStage = "pred";
                return conStage + " " + String.valueOf(standartAmount);
        }
        return Broadcaster.noResult;
    }
}
