package Foundation.Flora;

import Foundation.Broadcaster;
import Foundation.Ground;

public class Flora extends Broadcaster{

    private Ground ground;
    private int treeAmount;
    private int wildPlantsAmount;
    private int cultivatedPlantsAmount;

    public Flora(Ground ground){
        this.ground = ground;
        int max = ground.getCapacity();
        treeAmount = max/2;
        wildPlantsAmount = max/2;
        cultivatedPlantsAmount = 0;
    }

    public void run(){
       int max = ground.getCapacity();
       int delta = max - treeAmount - wildPlantsAmount;
       if (delta > 0){
           if (delta < 20) delta = 20;
           treeAmount += delta/20;
           wildPlantsAmount += delta/20;
       }
    }


    @Override
    public String getValue(String key) {
        switch (key){
            case "tree":
                return String.valueOf(treeAmount);
            case "wildPlants":
                return String.valueOf(wildPlantsAmount);
            case "cultivatedPlants":
                return String.valueOf(cultivatedPlantsAmount);
        }
        return Broadcaster.noResult;
    }
}
