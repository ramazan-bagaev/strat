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
    }

    abstract boolean contains(Date date);

    public boolean intersect(TimeDuration timeDuration){
        timeDurationIterator = new TimeDurationIterator(this, timeDuration);
        while(timeDurationIterator.hasNext()){
            Date date = timeDurationIterator.next();
            boolean b1 = this.contains(date);
            boolean b2 = timeDuration.contains(date);
            if ((b1 && b2)) return true;
        }
        return false;
    }

    public static void main(String[] args){
        TimeDuration a = new TimeDuration() {
            @Override
            boolean contains(Date date) {
                return date.weekDay == 2 && date.year > 500 && date.year < 510;
            }
        };
        a.hourUsing = true;
        a.weekUsing = true;
        a.yearUsing = true;

        TimeDuration b = new TimeDuration() {
            @Override
            boolean contains(Date date) {
                return (date.month >= 6 && date.month <= 8) && (date.day == 5);
            }
        };
        b.monthUsing = true;
        b.dayUsing = true;
        System.out.println(a.intersect(b));
    }
}
