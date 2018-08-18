package Foundation.Time;

import java.util.ArrayList;

public class TimeDurationIterator {

    private boolean hasNext;

    private boolean hourUsing;
    private boolean dayUsing;
    private boolean weekUsing;
    private boolean monthUsing;
    private boolean yearUsing;

    private boolean dayAndWeekUsing;

    private Date iterDate;

    public TimeDurationIterator(TimeDuration timeDuration1, TimeDuration timeDuration2){
        this.hourUsing = timeDuration1.hourUsing || timeDuration2.hourUsing;
        this.dayUsing = timeDuration1.dayUsing || timeDuration2.dayUsing;
        this.weekUsing = timeDuration1.weekUsing || timeDuration2.weekUsing;
        this.monthUsing = timeDuration1.monthUsing || timeDuration2.monthUsing;
        this.yearUsing = timeDuration1.yearUsing || timeDuration2.yearUsing;
        this.dayAndWeekUsing = this.dayUsing && this.weekUsing;
        this.hasNext = true;
        initIterDate();
    }

    public TimeDurationIterator(ArrayList<TimeDuration> timeDurations){
        this.hourUsing = false;
        this.dayUsing = false;
        this.weekUsing = false;
        this.monthUsing = false;
        this.yearUsing = false;
        for(TimeDuration timeDuration: timeDurations){
            this.hourUsing |= timeDuration.hourUsing;
            this.dayUsing |= timeDuration.dayUsing;
            this.weekUsing |= timeDuration.weekUsing;
            this.monthUsing |= timeDuration.monthUsing;
            this.yearUsing |= timeDuration.yearUsing;
        }

        this.dayAndWeekUsing = this.dayUsing && this.weekUsing;
        this.hasNext = true;
        initIterDate();
    }

    private void initIterDate(){
        this.iterDate = new Date();
        if (!hourUsing) iterDate.hour = Date.ANY_HOUR;
        if (!dayUsing) iterDate.day = Date.ANY_DAY;
        if (!weekUsing) iterDate.weekDay = Date.ANY_WEEKDAY;
        if (!monthUsing) iterDate.month = Date.ANY_MONTH;
        if (!yearUsing) iterDate.year = Date.ANY_YEAR;
    }

    public boolean hasNext(){
        return hasNext;
    }

    public Date next(){
        boolean overFlow = false;
        boolean iterStart = true;
        if (hourUsing){
            iterDate.hour += 1;
            iterStart = false;
            if (iterDate.hour == Date.END_HOUR){
                iterDate.hour = Date.START_HOUR;
                overFlow = true;
                hasNext = false;
            }
        }
        if (dayUsing || weekUsing){
            if (iterStart || overFlow) {
                hasNext = true;
                iterDate.weekDay += 1;
                iterDate.day += 1;
                iterStart = false;
                overFlow = false;
            }
            if (iterDate.weekDay == Date.END_WEEKDAY) {
                iterDate.weekDay = Date.START_WEEKDAY;
                if (!dayUsing){
                    overFlow = true;
                    hasNext = false;
                }
            }
            if (iterDate.day == Date.END_DAY){
                iterDate.day = Date.START_DAY;
                overFlow = true;
                hasNext = false;
            }
        }
        if (monthUsing || dayAndWeekUsing){
            if (iterStart || overFlow){
                iterDate.month += 1;
                iterStart = false;
                overFlow = false;
                hasNext = true;
            }
            if (iterDate.month == Date.END_MONTH){
                iterDate.month = Date.START_MONTH;
                overFlow = true;
                hasNext = false;
            }
        }
        if (yearUsing || dayAndWeekUsing){
            if (iterStart || overFlow){
                iterDate.year += 1;
                hasNext = true;
            }
            if (iterDate.year == Date.END_YEAR){
                hasNext = false;
            }
        }
        return iterDate;
    }
}
