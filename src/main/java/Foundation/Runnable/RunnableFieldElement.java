package Foundation.Runnable;

import Foundation.Elements.FieldElement;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time;

public abstract class RunnableFieldElement extends FieldElement implements RunEntity{

    public RunnableFieldElement(Type type, Field parent) {
        super(type, parent);
    }

    @Override
    public void run(){
    }
}
