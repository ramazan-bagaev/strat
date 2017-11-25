package ResourceConvertors;

import Foundation.*;

public class TreeConvertor extends ResourceConvertor{

    public TreeConvertor(Field field){
        super();

        Resource.Type outputType = Resource.Type.Tree;
        setOutput(outputType);

        Element additionalElement = field.getAdditionalElement();
        if (additionalElement.getType() == Element.Type.Tree){
            Tree tree = (Tree)additionalElement;

            ResourceCause resCause = tree.getResourceCause();
            setResourceCause(resCause);
        }
    }

    @Override
    public Resource convert() {
        if (getPeople() == null) return null;
        int humanHour = 10 * getPeople().getAmount();
        int treeAmount = getResourceCause().consume(humanHour);
        return new Resource(getOutput(), treeAmount);
    }
}
