package Foundation.Flora;

import Foundation.Broadcaster;
import Foundation.Climate;
import Foundation.Ecosystem;
import Foundation.Ground;

import java.util.ArrayList;

public class Flora extends Broadcaster{

    private Ecosystem ecosystem;
    private Ground ground;

    private ArrayList<Plant> plants;

    public Flora(Ecosystem ecosystem, Ground ground){
        this.ecosystem = ecosystem;
        this.ground = ground;
        plants = new ArrayList<>();
        if (ground.getGroundType() == Ground.GroundType.Soil) {
            plants.add(new Berry(100000));
        }
        if (ground.getGroundType() == Ground.GroundType.Mud) {
            plants.add(new Berry(10000));
        }
        //int max = ground.getCapacity();
    }

    public void run(){
        for (Plant plant: plants){
            plant.run();
        }
    }


    @Override
    public String getValue(String key) {
        switch (key){
        }
        return Broadcaster.noResult;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }
}
