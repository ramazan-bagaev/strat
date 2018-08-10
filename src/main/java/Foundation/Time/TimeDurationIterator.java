package Foundation.Time;

public class TimeDurationIterator {

    private TimeDuration timeDuration;
    private Date date;

    public TimeDurationIterator(TimeDuration timeDuration){
        this.timeDuration = timeDuration;
    }

    public boolean hasNext(){
        return false;
    }

    public Date next(){
        return null;
    }
}
