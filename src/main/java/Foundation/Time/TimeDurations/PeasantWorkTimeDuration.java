package Foundation.Time.TimeDurations;

import Foundation.Time.Date;
import Foundation.Time.TimeDuration;

public class PeasantWorkTimeDuration extends TimeDuration {

    private int startHour;
    private int endHour;

    public PeasantWorkTimeDuration(int startHour, int endHour){
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public boolean contains(Date date) {
        return date.hour >= startHour && date.hour <= endHour;
    }
}
