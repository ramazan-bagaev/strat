package Foundation.Time;

import Foundation.Time.TimeDurations.WeeklyTimeDuration;
import Foundation.Works.Work;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<ScheduleItem> items;

    public Schedule(){
        items = new ArrayList<>();
    }

    public void addWork(Work work, TimeDuration timeDuration){
        for(ScheduleItem scheduleItem : items){
            if (scheduleItem.timeDuration.intersect(timeDuration)) return;
        }
        items.add(new ScheduleItem(work, timeDuration));
    }

    public Work getWork(Date date){
        for(ScheduleItem scheduleItem: items){
            if (scheduleItem.timeDuration.contains(date)) return scheduleItem.work;
        }
        return null;
    }

    public boolean isFree(TimeDuration timeDuration){
        for(ScheduleItem scheduleItem: items){
            if (scheduleItem.timeDuration.intersect(timeDuration)) return false;
        }
        return true;
    }


    public boolean contains(Work work, Date date){
        for(ScheduleItem scheduleItem: items){
            if (scheduleItem.work == work){
                return (scheduleItem.timeDuration.contains(date));
            }
        }
        return false;
    }

    public TimeDuration giveFreeWeeklyTimeDuration(int hourAmount){
        ArrayList<TimeDuration> timeDurations = new ArrayList<>();
        for(ScheduleItem item: items) timeDurations.add(item.timeDuration);
        Date date = new Date();
        date.year = Date.ANY_YEAR;
        date.month = Date.ANY_MONTH;
        date.day = Date.ANY_DAY;

        TimeDurationIterator iter = new TimeDurationIterator(timeDurations);
        while (iter.hasNext()){
            return null;
        }
        return null;
    }
}
