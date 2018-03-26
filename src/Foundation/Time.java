package Foundation;

import Utils.Broadcaster;
import Utils.Content;
import Utils.Subscription;

public class Time implements Broadcaster {

    private Content dateContent;

    private Date date;
    private boolean isNewWeek;
    private boolean isNewMonth;
    private boolean isNewYear;

    public Time(){
        date = new Date();
        dateContent = new Content();
    }

    public void nextDay(){
        date.day +=1;
        date.weekDay +=1;
        if (date.weekDay == 8){
            date.weekDay = 1;
            isNewWeek = true;
        }
        if (date.weekDay == 2) isNewWeek = false;
        if (date.day == 31){
            date.day = 1;
            isNewMonth = true;
            date.month +=1;
            if (date.month == 13){
                date.month = 1;
                isNewYear = true;
                date.year +=1;
            }
            else{
                isNewYear = false;
            }
        }
        else{
            isNewYear = false;
            isNewMonth = false;
        }
        dateContent.changed();
    }

    public boolean isNewWeek(){
        return isNewWeek;
    }

    public boolean isNewMonth(){
        return isNewMonth;
    }

    public boolean isNewYear(){
        return isNewYear;
    }

    public Date getDate(){
        return date;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "day":
                return String.valueOf(date.day);
            case "month":
                return String.valueOf(date.month);
            case "year":
                return String.valueOf(date.year);
        }
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "time":
                dateContent.subscribe(subscription);
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "time":
                dateContent.unsubscribe(subscription);
        }
    }
}
