package Foundation;

public class Work {

    private People people;

    private ResourceConvertor resourceConvertor;

    public Work(People people, ResourceConvertor resourceConvertor){
        setPeople(people);
        setResourceConvertor(resourceConvertor);
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public ResourceConvertor getResourceConvertor() {
        return resourceConvertor;
    }

    public void setResourceConvertor(ResourceConvertor resourceConvertor) {
        this.resourceConvertor = resourceConvertor;
    }
}
