package Foundation.WorksP;

import Foundation.ResourceStore;

public abstract class Work {

    protected ResourceStore resourceStore;
    private int id;

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

}
