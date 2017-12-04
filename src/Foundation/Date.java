package Foundation;

public class Date {

    public static final int START_DAY = 1;
    public static final int START_WEEKDAY = 1;
    public static final int START_MONTH = 1;
    public static final int START_YEAR = 0;

    public int day;
    public int weekDay;
    public int month;
    public int year;

    public Date(){
        this.day = START_DAY;
        this.weekDay = START_WEEKDAY;
        this.month = START_MONTH;
        this.year = START_YEAR;
    }
}
