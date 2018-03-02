package Foundation.Fauna;

import Foundation.Ecosystem;
import Foundation.Flora.Flora;
import Foundation.Ground;
import Foundation.Resource;

import java.util.ArrayList;

public class Fauna{

    private Ecosystem ecosystem;
    private Flora flora;
    private ArrayList<Animal> animals;

    public Fauna(Ecosystem ecosystem){
        this.ecosystem = ecosystem;
        this.flora = ecosystem.getFlora();
        animals = new ArrayList<>();
        Ground.GroundType groundType = ecosystem.getClimate().getGroundType();
        if (groundType == Ground.GroundType.Soil){
            animals.add(new Herbivores(100000));
        }
        if (groundType == Ground.GroundType.Mud){
            animals.add(new Herbivores(10000));
        }
    }

    public void run(){
        for (Animal animal: animals){
            animal.run();
        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
