package Foundation.Works.Occupation;

import Foundation.GameEngine;
import Foundation.Person.People;
import Foundation.Runnable.RunEntity;
import Foundation.Works.Work;

import java.util.ArrayList;
import java.util.HashMap;

public class Occupation implements RunEntity {

    protected ArrayList<Work> workGroup;
    protected GameEngine gameEngine;

    public Occupation(GameEngine gameEngine){
        workGroup = new ArrayList<>();
        this.gameEngine = gameEngine;
    }

    public void addWork(Work work){
        workGroup.add(work);
    }

    @Override
    public void run() {
        for(Work work: workGroup){
            if (work.isAvailable()) work.doWork();
        }
        if (allWorksDone()) gameEngine.removeRunEntity(this);
    }

    public boolean allWorksDone(){
        for(Work work: workGroup){
            if (work.isAvailable()) return false;
        }
        return true;
    }
}
