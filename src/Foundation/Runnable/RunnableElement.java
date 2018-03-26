package Foundation.Runnable;

import Foundation.Elements.Element;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;

public abstract class RunnableElement extends Element implements RunEntity{

    public RunnableElement(Type type, Time time, Field parent, FieldMap map) {
        super(type, time, parent, map);
    }

    @Override
    public void run(){
    }
}
