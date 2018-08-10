package Foundation.Time;

public class TimeDurationIterator {

    private TimeDuration timeDuration;
    private Date iterDate;

    public TimeDurationIterator(TimeDuration timeDuration){
        this.timeDuration = timeDuration;
        this.iterDate = new Date();
    }

    public boolean hasNext(){
        if (timeDuration.yearUsing) return (iterDate.year != Date.END_YEAR);
        if (timeDuration.monthUsing) return (iterDate.year != Date.START_YEAR + 1);
        if (timeDuration.dayUsing) return (iterDate.month != Date.START_MONTH + 1);
        if (timeDuration.weekUsing) return (iterDate.day < Date.END_WEEKDAY);
        if (timeDuration.hourUsing) return (iterDate.day != Date.START_DAY + 1);
        return false;
    }

    public Date next(){
        if (timeDuration.hourUsing){
            iterDate.nextHour();
            return iterDate;
        }
        if (timeDuration.dayUsing || timeDuration.weekUsing){
            iterDate.nextDay();
            return iterDate;
        }
        if (timeDuration.monthUsing){
            iterDate.nextMonth();
            return iterDate;
        }
        if (timeDuration.yearUsing){
            iterDate.nextYear();
            return iterDate;
        }
        return iterDate;
    }
}
