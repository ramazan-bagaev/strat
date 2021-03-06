package WindowElements;

import Foundation.*;
import Foundation.BasicShapes.StringShape;
import Foundation.Time.Time;
import Utils.Geometry.Coord;
import Utils.Subscription;
import Windows.Window;

public class TimeElement extends Label {

    private Subscription dateSubscription;
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
        dateSubscription = new Subscription() {
            @Override
            public void changed() {
                setShapes();
            }
        };
        time.subscribe("time", dateSubscription);
    }

    public void setShapes(){
        String newDay = String.valueOf(time.getDate().day);
        String newMonth = String.valueOf(time.getDate().month);
        String newYear = String.valueOf(time.getDate().year);
        clearBasicShapes();
        day = newDay;
        month = newMonth;
        year = newYear;
        String fineDay = new String(day);
        String fineMonth = new String(month);
        if (day.length() != 2) fineDay = "0"+day;
        if (month.length() != 2) fineMonth = "0"+month;
        String time = fineDay + ":" + fineMonth + ":" + year;
        StringShape stringShape = new StringShape(new Coord(0, 0), getSize(), time, new Color(Color.Type.Black), getParent().getFont("latin"));
        addBasicShapes(stringShape.getBasicShapes());
    }

}
