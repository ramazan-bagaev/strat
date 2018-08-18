package Foundation.Time;

import Foundation.Works.Work;

public class ScheduleItem {

    public TimeDuration timeDuration;
    public Work work;

    public ScheduleItem(Work work, TimeDuration timeDuration){
        this.work = work;
        this.timeDuration = timeDuration;
    }
}
