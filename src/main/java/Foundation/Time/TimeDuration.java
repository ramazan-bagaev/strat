package Foundation.Time;

public abstract class TimeDuration {

    public boolean hourUsing;
    public boolean dayUsing;
    public boolean weekUsing;
    public boolean monthUsing;
    public boolean yearUsing;

    private TimeDurationIterator timeDurationIterator;

    public TimeDuration(){
        hourUsing = false;
        dayUsing = false;
        weekUsing = false;
        monthUsing = false;
        yearUsing = false;
        this.timeDurationIterator = new TimeDurationIterator(this);
    }

    abstract boolean contains(Date date);

    public boolean intesect(TimeDuration timeDuration){
        while(timeDurationIterator.hasNext()){
            Date date = timeDurationIterator.next();
            boolean b1 = this.contains(date);
            boolean b2 = timeDuration.contains(date);
            if ((b1 && b2)) return true;
        }
        return false;
    }
}
