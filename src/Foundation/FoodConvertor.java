package Foundation;

import java.util.ArrayList;

public class FoodConvertor  extends ResourceConvertor{

    public FoodConvertor(Field field, City city, int desirableAmount){
        super();
        ArrayList<Resource.Type> inputTypes = new ArrayList<>();
        inputTypes.add(Resource.Type.HumanHour);
        setInput(inputTypes);

        Resource.Type outputType = Resource.Type.Food;
        setOutput(outputType);

        Ground ground = field.getGround();
        ResourceCause resCause = ground.getResourceCause();
        setResourceCause(resCause);

        setDesirableAmount(desirableAmount);

        ResourceStore store = city.getResourceStore();

        ArrayList<ResourceBank> banks = new ArrayList<>();
        ArrayList<Resource.Type> types = getInput();

        for(Resource.Type type: types){
            ResourceBank bank = store.getResourceBank(type);
            if (bank == null) bank = new ResourceBank(type, 0);
            banks.add(bank);
        }

        setResourceBanks(banks);
    }

    @Override
    public Resource convert() {
        Resource humanhour = getResourceBank(Resource.Type.HumanHour).getResource(getDesirableAmount());
        int consum = getResourceCause().consume(humanhour.getAmount());
        Resource result = new Resource(getOutput(), consum);
        return result;
    }
}
