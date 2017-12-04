package Foundation;

public class Time {

    private Date date;
    private boolean isNewWeek;
    private boolean isNewMonth;
    private boolean isNewYear;

    public Time(){
        date = new Date();
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

}
