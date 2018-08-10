package Foundation.Works;

import Foundation.Person.People;
import Foundation.Works.Occupation.Occupation;

import java.util.ArrayList;

public abstract class Work{



    protected Occupation occupation;

    protected People people;

    private boolean available;

    protected int stage;
    protected int endStage;

    protected ArrayList<Work> nextAvailableWorks;

    public Work(People people, Occupation occupation){
        this.people = people;
        this.occupation = occupation;
        available = true;
        nextAvailableWorks = new ArrayList<>();
        stage = 0;
        endStage = 0;
    }

    public void addNextAvailableWork(Work work){
        nextAvailableWorks.add(work);
    }

    public void doFinishingWork(){
        available = false;
        stage = 0;
        for(Work work: nextAvailableWorks){
            work.setAvailable(true);
        }
    }

    protected void nextStage(){
        stage++;
    }

    protected boolean isFinished(){
        return (stage >= endStage);
    }

    protected boolean isStarted(){
        return (stage == 0);
    }

    protected abstract void doMainWork();

    public int getDuration(){
        return endStage;
    }

    public void doWork(){
        doMainWork();
        nextStage();
        if (isFinished()) doFinishingWork();
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
