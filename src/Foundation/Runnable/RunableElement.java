package Foundation.Runnable;

import Foundation.Elements.Element;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;

public abstract class RunableElement extends Element implements RunEntity{

    public RunableElement(Type type, Time time, Field parent, FieldMap map) {
        super(type, time, parent, map);
    }
}
