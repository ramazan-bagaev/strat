package Foundation.Flora;

import Foundation.Products.Product;

import java.util.ArrayList;

public class Tree extends Plant {

    public Tree(int standartAmount){
        super(standartAmount);
        name = "tree";
    }

    @Override
    public boolean isEdible(){
        return false;
    }
}
