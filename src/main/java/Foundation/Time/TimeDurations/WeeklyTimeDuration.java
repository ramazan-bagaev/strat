package Foundation.Time.TimeDurations;

import Foundation.Time.Date;
import Foundation.Time.TimeDuration;

import java.util.ArrayList;

public class WeeklyTimeDuration extends TimeDuration {

    private ArrayList<Integer> hours;
    private ArrayList<Integer> weekdays;

    public WeeklyTimeDuration(int beginHour, int endHour){
        this.hours = new ArrayList<>();
        this.weekdays = new ArrayList<>();
        for(int hour = beginHour; hour < endHour; hour++) hours.add(hour);
        this.hourUsing = true;
        this.weekUsing = true;
    }

    public WeeklyTimeDuration(){
        this.hours = new ArrayList<>();
        this.weekdays = new ArrayList<>();
        for(int hour = Date.START_HOUR; hour < Date.END_HOUR; hour++) hours.add(hour);
        for(int weekday = Date.START_WEEKDAY; weekday < Date.END_WEEKDAY; weekday++) weekdays.add(weekday);
    }

    public void addWorkingDay(int weekday){
        weekdays.add(weekday);
    }

    public void removeWorkingDay(int weekday){
        weekdays.remove(new Integer(weekday));
    }

    public void removeWorkingHour(int hour){
        hours.remove(new Integer(hour));
    }

    public ArrayList<Integer> getHours() {
        return hours;
    }

    public ArrayList<Integer> getWeekdays() {
        return weekdays;
    }

    @Override
    public boolean mainContains(Date date) {
        return hours.contains(date.hour) && weekdays.contains(date.weekDay);
    }
}
