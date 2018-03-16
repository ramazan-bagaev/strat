package Foundation;

import Foundation.Elements.City;

public abstract class Work {

    protected ResourceStore resourceStore;
    private int id;
    private Type type;

    public Work(ResourceStore resourceStore){
        this.resourceStore = resourceStore;
    }

    public abstract void doJob();

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public enum Type{
        Hunting, Gathering
    }
}
