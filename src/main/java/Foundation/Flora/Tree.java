package Foundation.Flora;

import Foundation.Recources.Resource;

import java.util.ArrayList;

public class Tree extends Plant {

    public Tree(int standartAmount){
        super(standartAmount);
        name = "tree";
    }

    @Override
    public ArrayList<Resource> getResources(int amount) {
        ArrayList<Resource> resources = new ArrayList<>();
        return null;
    }

    @Override
    public boolean isEdible(){
        return false;
    }
}
