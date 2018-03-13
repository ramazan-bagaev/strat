package WindowElements;

import CharacterShape.CharacterShape;
import Foundation.*;

import java.util.ArrayList;

public class TimeElement extends Label {

    private String day;
    private String month;
    private String year;
    private Time time;

    public TimeElement(Coord pos, Coord size, Time time, Window parent){
        super(pos, size, "", parent);
        this.time = time;
        day = String.valueOf(time.getDate().day);
        month = String.valueOf(time.getDate().month);
        year = String.valueOf(time.getDate().year);

        setShapes();
    }

    public void setShapes(){
        String newDay = String.valueOf(time.getDate().day);
        String newMonth = String.valueOf(time.getDate().month);
        String newYear = String.valueOf(time.getDate().year);
        ArrayList<BasicShape> basicShapes = getBasicShapes();
        basicShapes.clear();
        day = newDay;
        month = newMonth;
        year = newYear;
        String fineDay = new String(day);
        String fineMonth = new String(month);
        if (day.length() != 2) fineDay = "0"+day;
        if (month.length() != 2) fineMonth = "0"+month;
        String time = fineDay + ":" + fineMonth + ":" + year;
        StringShape stringShape = new StringShape(getPos(), getSize(), time, new Color(Color.Type.Black), getParent().getFont("latin"));
        basicShapes.addAll(stringShape.getBasicShapes());
    }

    public void run(){
        String newDay = String.valueOf(time.getDate().day);
        if (!newDay.equals(day)){
            setShapes();
        }
    }

}
