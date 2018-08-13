package Foundation.Time;

public class Date {

    public static final int START_HOUR = 0;
    public static final int START_DAY = 1;
    public static final int START_WEEKDAY = 1;
    public static final int START_MONTH = 1;
    public static final int START_YEAR = 0;

    public static final int END_HOUR = 24;
    public static final int END_DAY = 31;
    public static final int END_WEEKDAY = 8;
    public static final int END_MONTH = 13;
    public static final int END_YEAR = 2000;

    public int hour;
    public int day;
    public int weekDay;
    public int month;
    public int year;

    public Date(){
        this.hour = START_HOUR;
        this.day = START_DAY;
        this.weekDay = START_WEEKDAY;
        this.month = START_MONTH;
        this.year = START_YEAR;
    }

    public Date(int days){
        this.year = days / 361;
        days = days % 361;
        this.month = days / 31;
        days = days % 31;
        this.day = days;
        this.weekDay = START_WEEKDAY;
    }

    public Date(int hour, int day, int month, int year, int weekDay){
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
        this.weekDay = weekDay;
    }

    public Date(Date date){
        setDate(date);
    }

    public boolean sameAs(Date date){
        return (hour == date.hour && day == date.day && month == date.month && year == date.year);
    }

    public void nextHour(){
        hour += 1;
        normalize();
    }

    public void nextDay(){
        day += 1;
        normalize();
    }

    public void nextMonth(){
        month += 1;
        normalize();
    }

    public void nextYear(){
        year += 1;
    }

    public Date add(Date date){
        int hour = this.hour + date.hour;
        int day = this.day + date.day + hour/ END_HOUR;
        hour = hour % END_HOUR;
        int month = this.month + date.month + day / END_DAY;
        day = day % END_DAY;
        int year = this.year + date.year + month / END_MONTH;
        month = month % END_MONTH;
        return new Date(hour, day, month, year, weekDay);
    }

    public void normalize(){
        day = day + hour / END_HOUR;
        weekDay = (weekDay + hour / END_HOUR) % END_WEEKDAY;
        hour = hour % END_HOUR;
        month = month + day / END_DAY;
        day = day % END_DAY;
        year = year + month / END_MONTH;
        month = month % END_MONTH;
    }

    public void setDate(Date date){
        this.hour = date.hour;
        this.day = date.day;
        this.weekDay = date.weekDay;
        this.month = date.month;
        this.year = date.year;
    }

    public void print(String text){
        System.out.println(text +": hour = " + hour + "; day = " + day + "; month = " +  month + "; year = " + year);
    }

}
