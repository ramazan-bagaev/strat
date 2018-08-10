package Foundation.Time;

import Utils.Broadcaster;
import Utils.Content;
import Utils.Subscription;

public class Time implements Broadcaster {

    private Content dateContent;

    private Date date;
    public Time(){
        date = new Date();
        dateContent = new Content();
    }

    public void next(){
        date.nextDate();
        dateContent.changed();
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
