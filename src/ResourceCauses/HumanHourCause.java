package ResourceCauses;

import Foundation.Population;
import Foundation.Resource;
import Foundation.ResourceCause;

public class HumanHourCause extends ResourceCause {

    Population population;

    public HumanHourCause(Population population){
        super(Resource.Type.HumanHour);
    }

    @Override
    public void renew(){
        setCapacity(population.workingPopulation());
    }

}
