package Foundation.Time;

import Utils.TimeMeasurer;

import java.util.ArrayList;

public class TimeDuration {

    private ArrayList<TimeDuration> additional;

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
        additional = new ArrayList<>();
    }

    public TimeDuration(ArrayList<TimeDuration> additional){
        this.additional = additional;
        hourUsing = false;
        dayUsing = false;
        weekUsing = false;
        monthUsing = false;
        yearUsing = false;
        initFlags(additional);
    }

    private void initFlags(ArrayList<TimeDuration> timeDurations){
        for(TimeDuration timeDuration: timeDurations) {
            renewFlags(timeDuration);
        }
    }

    private void renewFlags(TimeDuration timeDuration){
        this.hourUsing |= timeDuration.hourUsing;
        this.dayUsing |= timeDuration.dayUsing;
        this.weekUsing |= timeDuration.weekUsing;
        this.monthUsing |= timeDuration.monthUsing;
        this.yearUsing |= timeDuration.yearUsing;
    }

    public boolean mainContains(Date date){
        return false;
    }

    public boolean contains(Date date){
        if (checkAdditional(date)) return true;
        return mainContains(date);
    }

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

    public void addAdditional(TimeDuration timeDuration){
        additional.add(timeDuration);
        renewFlags(timeDuration);
    }

    private boolean checkAdditional(Date date){
        for(TimeDuration timeDuration: additional){
            if (timeDuration.contains(date)) return true;
        }
        return false;
    }
}
