package Foundation;

import java.util.ArrayList;

public class Works {

    private ArrayList<Work> works;
    private int currentId;

    public Works(){
        works = new ArrayList<>();
        currentId = 0;
    }

    public void addWork(Work work){
        currentId++;
        work.setId(currentId);
        works.add(work);
    }

    public void run(){
        for(Work work: works){
            work.doJob();
        }
    }
}
