package Foundation.Works.Occupation;

import Foundation.GameEngine;
import Foundation.Person.People;
import Foundation.Runnable.RunEntity;
import Foundation.Time.Time;
import Foundation.Works.Work;

import java.util.ArrayList;
import java.util.HashMap;

public class Occupation implements RunEntity {

    protected ArrayList<Work> workGroup;
    protected GameEngine gameEngine;
    protected Time time;

    public Occupation(GameEngine gameEngine){
        workGroup = new ArrayList<>();
        this.gameEngine = gameEngine;
        this.time = gameEngine.getTime();
    }

    public void addWork(Work work){
        workGroup.add(work);
    }

    @Override
    public void run() {
        for(Work work: workGroup){
            if (work.isAvailable()) work.doWork(time);
        }
        if (allWorksDone()) initNewCycle();
    }

    private void initNewCycle(){
        for(Work work: workGroup){
            work.setFinished(false);
        }
    }

    public boolean allWorksDone(){
        for(Work work: workGroup){
            if (work.isAvailable()) return false;
        }
        return true;
    }
}
