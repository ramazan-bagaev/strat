package Foundation.Works;

import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Time.Date;
import Foundation.Time.Time;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;

import java.util.ArrayList;

public abstract class Work{


    protected Occupation occupation;

    protected People people;

    private boolean finished;

    protected int stage;
    protected int endStage;

    protected ArrayList<Work> previousWorks;

    public Work(Occupation occupation){
        this.people = new People();
        this.occupation = occupation;
        finished = false;
        previousWorks = new ArrayList<>();
        stage = 0;
        endStage = 0;
    }

    public void addPreviousWork(Work work){
        previousWorks.add(work);
    }

    public void doFinishingWork(){
        finished = true;
        stage = 0;
    }

    public void addPerson(Person person, TimeDuration timeDuration){
        if (person.isAbleToWork(timeDuration)){
            people.addPerson(person);
            person.addWork(this, timeDuration);
        }
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }


    protected void nextStage(){
        stage++;
    }

    private boolean isFinished(){
        return finished;
    }

    public boolean isAvailable(){
        if (finished) return false;
        for(Work work: previousWorks){
            if (!work.isFinished()) return false;
        }
        return true;
    }

    protected boolean isEndingStage(){
        return (stage >= endStage);
    }

    protected boolean isBeginningStage(){
        return (stage == 0);
    }

    protected abstract void doMainWork();

    public void doWork(Time time){
        if (getWorkingAmount(time.getDate()) == 0) return;
        doMainWork();
        nextStage();

        if (isEndingStage()) doFinishingWork();
    }

    private int getWorkingAmount(Date date){
        int amount = 0;
        for(Person person: people.getPersonArray()) {
            if (person.isAbleToWork(this, date)) amount++;
        }
        return amount;
    }

    public ArrayList<Work> getPreviousWorks() {
        return previousWorks;
    }
}
