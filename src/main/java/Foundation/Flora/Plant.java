package Foundation.Flora;

import Foundation.Products.Product;

import java.util.ArrayList;

public abstract class Plant {

    protected int amount;
    private int standartAmount;
    protected int plantId;
    protected String name;

    public Plant(int standartAmount){
        this.amount = standartAmount;
        this.standartAmount = standartAmount;
    }

    public void run(){
        amount = standartAmount;
    }

    public int getAmount(){
        return amount;
    }


    public abstract boolean isEdible();
}
