package Foundation.Fauna;

import Utils.Subscription;

import java.util.ArrayList;

public class Herbivores extends Animal{

    public Herbivores(int amount){
        super(2, amount);
        name = "herbivores";
    }

    @Override
    public boolean isHuntable(){
        return true;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {

    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {

    }
}
