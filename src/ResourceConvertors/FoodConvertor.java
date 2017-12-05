package ResourceConvertors;


import Foundation.*;

public class FoodConvertor  extends ResourceConvertor {

    public FoodConvertor(Field field){
        super();

        Resource.Type outputType = Resource.Type.Food;
        setOutput(outputType);

        Ecosystem ecosystem = field.getEcosystem();
        ResourceCause resCause = ecosystem.getResourceCause();
        setResourceCause(resCause);
    }

    @Override
    public Resource convert() {
        if (getPeople() == null) return null;
        int humanHour = 10 * getPeople().getAmount();
        int fertility = getResourceCause().consume(humanHour);
        return new Resource(getOutput(), fertility);
    }
}
