package Foundation;

import java.util.ArrayList;

public class Production {

    private ArrayList<ResourceConvertor> resourceConvertors;

    public Production(){
        resourceConvertors = new ArrayList<>();
    }

    public ArrayList<ResourceConvertor> getResourceConvertors() {
        return resourceConvertors;
    }

    public void setResourceConvertors(ArrayList<ResourceConvertor> resourceConvertors) {
        this.resourceConvertors = resourceConvertors;
    }

    public void addResourceConvertor(ResourceConvertor resourceConvertor){
        resourceConvertors.add(resourceConvertor);
    }

    public ArrayList<Resource> run(){
        ArrayList<Resource> result = new ArrayList<>();
        for (ResourceConvertor resourceConvertor: resourceConvertors){
            result.add(resourceConvertor.convert());
        }
        return result;
    }
}

