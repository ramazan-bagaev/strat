package ResourceConvertors;

import Foundation.*;

public class RockConvertor extends ResourceConvertor{

    public RockConvertor(Field field){
        super();

        Resource.Type outputType = Resource.Type.Stone;
        setOutput(outputType);

        Element additionalElement = field.getAdditionalElement();
        if (additionalElement.getType() == Element.Type.Rock){
            Rock rock = (Rock)additionalElement;

            ResourceCause resCause = rock.getResourceCause();
            setResourceCause(resCause);
        }
    }

    @Override
    public Resource convert() {
        if (getPeople() == null) return null;
        int humanHour = 10 * getPeople().getAmount();
        int rockAmount = getResourceCause().consume(humanHour);
        return new Resource(getOutput(), rockAmount);
    }
}

