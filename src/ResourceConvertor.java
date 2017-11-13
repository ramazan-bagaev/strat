import java.util.ArrayList;

abstract public class ResourceConvertor {

    private ResourceCause resourceCause; // if have one, arbitary
    private ArrayList<Resource.Type> input;
    private Resource.Type output;
    private ArrayList<ResourceBank> resourceBanks; // where from resources can be extracted

    private int desirableAmount;

    public ResourceConvertor(){

    }

    public ResourceConvertor(ArrayList<Resource.Type> input, Resource.Type output){
        setInput(input);
        setOutput(output);
    }


    public ArrayList<Resource.Type> getInput() {
        return input;
    }

    public void setInput(ArrayList<Resource.Type> input) {
        this.input = input;
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

    public int getDesirableAmount() {
        return desirableAmount;
    }

    public void setDesirableAmount(int desirableAmount) {
        this.desirableAmount = desirableAmount;
    }
}