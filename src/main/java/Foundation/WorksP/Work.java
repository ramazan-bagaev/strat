package Foundation.WorksP;

import Foundation.Person.People;

public abstract class Work {

    protected People people;
    private int id;

    public Work(People people){
        this.people = people;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract void doJob();

}
