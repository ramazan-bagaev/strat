package Foundation.Works.Occupation;

import Foundation.Person.People;
import Foundation.Runnable.RunEntity;
import Foundation.Works.Work;

import java.util.ArrayList;
import java.util.HashMap;

public class Occupation implements RunEntity {

    protected ArrayList<Work> workGroup;

    public Occupation(){
        workGroup = new ArrayList<>();
    }

    public void addWork(Work work){
        workGroup.add(work);
    }

    @Override
    public void run() {
        for(Work work: workGroup){
            if (work.isAvailable()) work.doWork();
        }
    }
}
