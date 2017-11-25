package Foundation;

import java.util.ArrayList;

abstract public class ResourceConvertor {

    private ResourceCause resourceCause; // if have one, arbitary
    private People people;
    private Resource.Type output;
    private ArrayList<ResourceBank> resourceBanks; // where from resources can be extracted





    private ArrayList<Integer> resourceAmount;

    public ResourceConvertor(){

    }

    public ResourceConvertor(Resource.Type output, City city){
        setOutput(output);
    }


    public Resource.Type getOutput() {
        return output;
    }

    public void setOutput(Resource.Type output) {
        this.output = output;
    }

    public ResourceCause getResourceCause() {
        return resourceCause;
    }

    public void setResourceCause(ResourceCause resourceCause) {
        this.resourceCause = resourceCause;
    }

    public ArrayList<ResourceBank> getResourceBanks() {
        return resourceBanks;
    }

    public void setResourceBanks(ArrayList<ResourceBank> resourceBanks) {
        this.resourceBanks = resourceBanks;
    }

    public ResourceBank getResourceBank(Resource.Type type){
        for(ResourceBank resourceBank: resourceBanks){
            if (resourceBank.getType() == type) return resourceBank;
        }
        return null;
    }

    public abstract Resource convert();

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}