package Foundation.Time.TimeDurations;

import Foundation.Time.Date;
import Foundation.Time.TimeDuration;

import java.util.ArrayList;

public class OneWeekdayTimeDuration extends TimeDuration {

    private int weekday;
    private ArrayList<Integer> hours;

    public OneWeekdayTimeDuration(int weekDay){
        this.weekday = weekDay;
        this.hours = new ArrayList<>();
        this.hourUsing = true;
        this.weekUsing = true;
    }

    public OneWeekdayTimeDuration(int weekday, int beginHour, int endHour){
        this.weekday = weekday;
        this.hours = new ArrayList<>();
        for(int i = beginHour; i <= endHour; i++) hours.add(i);
        this.hourUsing = true;
        this.weekUsing = true;
    }

    public OneWeekdayTimeDuration(int weekday, ArrayList<Integer> hours){
        this.weekday = weekday;
        this.hours = hours;
        this.hourUsing = true;
        this.weekUsing = true;
    }

    public void removeHours(int hour){
        hours.remove(new Integer(hour));
    }

    public ArrayList<Integer> getHours() {
        return hours;
    }

    @Override
    public boolean mainContains(Date date) {
        return date.weekDay == weekday && hours.contains(date.hour);
    }
}
