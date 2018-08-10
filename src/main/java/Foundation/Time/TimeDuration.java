package Foundation.Time;

public abstract class TimeDuration {

    public boolean hourUsing;
    public boolean dayUsing;
    public boolean weekUsing;
    public boolean monthUsing;
    public boolean yearUsing;

    public TimeDuration(){
        hourUsing = false;
        dayUsing = false;
        weekUsing = false;
        monthUsing = false;
        yearUsing = false;
    }

    abstract boolean contains(Date date);

    abstract boolean intesect(TimeDuration timeDuration);
}
