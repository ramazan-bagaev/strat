package Foundation.Runnable;

import Foundation.Elements.FieldElement;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;

public abstract class RunnableFieldElement extends FieldElement implements RunEntity{

    public RunnableFieldElement(Type type, Time time, Field parent, FieldMap map) {
        super(type, time, parent, map);
    }

    @Override
    public void run(){
    }
}
