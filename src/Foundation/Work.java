package Foundation;

public abstract class Work {

    protected City city;
    protected People people;
    private int id;
    private Type type;

    public Work(City city){
        this.city = city;
    }

    public abstract void doJob();

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeople(People people){
        this.people = people;
    }

    public enum Type{
        Hunting, Gathering
    }
}
