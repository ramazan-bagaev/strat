package Foundation.Resources;

import Foundation.*;


public class Fur extends Resource{

    private String animalName;

    public Fur(int amount, String animalName) {
        super(Type.Fur, amount);
        this.animalName = animalName;
    }

    @Override
    public Image getImage(Coord pos, Coord size, Window parent) {
        return null;
    }

    @Override
    public boolean sameAs(Resource resource) {
        if (resource == null) return false;
        if (resource.getClass() != Fur.class) return false;
        Fur fur = (Fur)resource;
        return fur.animalName.equals(animalName);
    }

    public String getAnimalName(){
        return animalName;
    }

    public Resource getResource(int amount){
        amount = getRealAmount(amount);
        return new Fur(amount, animalName);
    }
}
