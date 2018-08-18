package Foundation.Time.TimeDurations;

import Foundation.Time.Date;
import Foundation.Time.TimeDuration;

import java.util.ArrayList;

public class WeeklyTimeDuration extends TimeDuration {

    private int beginHour;
    private int endHour;
    private ArrayList<Integer> notWorkingWeekday;

    public WeeklyTimeDuration(int beginHour, int endHour){
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.notWorkingWeekday = new ArrayList<>();
        this.hourUsing = true;
        this.weekUsing = true;
    }

    public void addNotWorkingDay(int weekday){
        if (weekday >= 1 && weekday <= 7){
            if (!notWorkingWeekday.contains(weekday)) notWorkingWeekday.add(weekday);
        }
    }

    @Override
    public boolean contains(Date date) {
        return date.hour >= beginHour && date.hour <= endHour && !notWorkingWeekday.contains(date.weekDay);
    }
}
