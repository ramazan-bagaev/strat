package Foundation;

public class Work {

    private People people;
    private int id;

    private ResourceConvertor resourceConvertor;

    public Work(People people, ResourceConvertor resourceConvertor, int id){
        setPeople(people);
        setResourceConvertor(resourceConvertor);
        this.id = id;
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

    public int getId(){
        return id;
    }
}
