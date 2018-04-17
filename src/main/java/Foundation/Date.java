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

    public Date(int days){
        this.year = days / 361;
        days = days % 361;
        this.month = days / 31;
        days = days % 31;
        this.day = days;
        this.weekDay = START_WEEKDAY;
    }

    public Date(int day, int month, int year, int weekDay){
        this.day = day;
        this.month = month;
        this.year = year;
        this.weekDay = weekDay;
    }

    public Date(Date date){
        this.day = date.day;
        this.weekDay = date.weekDay;
        this.month = date.month;
        this.year = date.year;
    }

    public boolean sameAs(Date date){
        return (day == date.day && month == date.month && year == date.year);
    }

    public Date add(Date date){
        int day = this.day + date.day;
        int month = this.month + date.month + day / 31;
        int year = this.year + date.year + month / 13;
        return new Date(day, month, year, weekDay);
    }

    public void setDate(Date date){
        this.day = date.day;
        this.weekDay = date.weekDay;
        this.month = date.month;
        this.year = date.year;
    }

    public void print(String text){
        System.out.println(text + ": day = " + day + "; month = " +  month + "; year = " + year);
    }

}
