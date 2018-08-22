package Foundation.Time;

import Foundation.Time.TimeDurations.OneWeekdayTimeDuration;
import Foundation.Time.TimeDurations.WeeklyTimeDuration;
import Foundation.Works.Work;

import java.util.ArrayList;
import java.util.Iterator;

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

    public TimeDuration getFreeWeekTimeDuration(int hourAmount){
        ArrayList<OneWeekdayTimeDuration> freeTimeDurations = new ArrayList<>();
        ArrayList<Integer> freeHourAmount = new ArrayList<>();
        for(int weekday = Date.START_WEEKDAY; weekday < Date.END_WEEKDAY; weekday++){
            OneWeekdayTimeDuration timeDuration = getFreeHour(weekday);
            if (timeDuration == null) continue;
            freeTimeDurations.add(timeDuration);
            freeHourAmount.add(timeDuration.getHours().size());
        }

        int wholeTime = 0;
        for(Integer weeklyHour: freeHourAmount) wholeTime += weeklyHour;
        if (wholeTime < hourAmount){
            return null;
        }

        // trying to extract full week
        WeeklyTimeDuration weekly = new WeeklyTimeDuration();
        ArrayList<Integer> resHours = weekly.getHours();
        for(OneWeekdayTimeDuration oneWeekday: freeTimeDurations){

            Iterator<Integer> iter = resHours.iterator();
            while(iter.hasNext()){
                int hour = iter.next();
                if (oneWeekday.getHours().contains(hour)) continue;
                iter.remove();
            }
            if (resHours.size() == 0) break;
        }

        if (resHours.size() != 0){
            int weekSize = weekly.getWeekdays().size();
            while(weekSize * resHours.size() > hourAmount) {
                weekly.removeWorkingHour(resHours.get(resHours.size() - 1));
            }
            if (weekSize * resHours.size() == hourAmount){
                return weekly;
            }
        }

        // failed to do that, try compose timeDuration from different oneWeekdayTimeDurations
        double part = 0;
        int delta = wholeTime - hourAmount;
        for(OneWeekdayTimeDuration weekdayTimeDuration: freeTimeDurations){
            ArrayList<Integer> hours = weekdayTimeDuration.getHours();
            part += (double)(delta*hours.size())/(double)(wholeTime);
            while(part >= 1){
                weekdayTimeDuration.removeHours(hours.get(hours.size() - 1));
                part--;
            }
        }
        if (part > 0){
            for(OneWeekdayTimeDuration weekdayTimeDuration: freeTimeDurations) {
                ArrayList<Integer> hours = weekdayTimeDuration.getHours();
                if (hours.size() == 0) continue;
                weekdayTimeDuration.removeHours(hours.get(hours.size() - 1));
                break;
            }
        }
        ArrayList<TimeDuration> finalTimeDuration = new ArrayList<>();
        for(OneWeekdayTimeDuration timeDuration: freeTimeDurations){
            finalTimeDuration.add(timeDuration);
        }
        TimeDuration result = new TimeDuration(finalTimeDuration);
        return result;
    }

    public TimeDuration getFreeDayTimeDuration(int hourAmount, int weekDay){
        OneWeekdayTimeDuration result = getFreeHour(weekDay);
        if (result == null) return null;
        ArrayList<Integer> hours = result.getHours();
        int amount = hours.size() - hourAmount;
        if (amount < 0) return null;
        for(int i = 0; i < amount; i++){
            int hour = hours.get(hours.size() - 1);
            result.removeHours(hour);
        }
        return result;
    }

    private OneWeekdayTimeDuration getFreeHour(int weekday){
        OneWeekdayTimeDuration weekDayTime = new OneWeekdayTimeDuration(weekday, 0, 23);
        ArrayList<TimeDuration> timeDurations = new ArrayList<>();
        for(ScheduleItem item: items) timeDurations.add(item.timeDuration);
        TimeDuration scheduleDuration = new TimeDuration(timeDurations);
        TimeDurationIterator iter = new TimeDurationIterator(scheduleDuration, weekDayTime);
        while (iter.hasNext()){
            Date date = iter.next();
            if (scheduleDuration.contains(date)) weekDayTime.removeHours(date.hour);
            if (weekDayTime.getHours().size() == 0){
                return null;
            }
        }
        return weekDayTime;
    }
}
