package Foundation.Time.TimeDurations;

import Foundation.Time.Date;
import Foundation.Time.TimeDuration;

public class OneDayAWeekActivityTimeDuration extends TimeDuration {

    private int startHour;
    private int endHour;
    private int weekDay;

    public OneDayAWeekActivityTimeDuration(int startHour, int endHour, int weekDay){
        this.startHour = startHour;
        this.endHour = endHour;
        this.weekDay = weekDay;
    }

    @Override
    public boolean contains(Date date) {
        return date.hour >= startHour && date.hour <= endHour && date.weekDay == weekDay;
    }
}
