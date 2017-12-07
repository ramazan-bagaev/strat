package Foundation;

public abstract class ResourceBank extends Broadcaster {

    protected int capacity;
    private Resource.Type type;

    public ResourceBank(Resource.Type type){
        this.type = type;
    }

    public ResourceBank(Resource.Type type, int capacity){
        this.capacity = capacity;
        this.type = type;
    }

    public Resource.Type getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int consume(int amount){
        Resource resource = getResource(amount);
        return resource.getAmount();
    }

    public boolean addResource(Resource resource){
        if (!canStore(resource)) return false;
        int amount = resource.getAmount();
        capacity = capacity + amount;
        resource.setAmount(0);
        return true;
    }

    public Resource getAll(){
        return getResource(getCapacity());
    }

    public abstract boolean canStore(Resource resource);

    public abstract Resource getResource(int amount);

    @Override
    public String getValue(String key) {
        switch (key){
            case "capacity":
                return String.valueOf(getCapacity());
        }
        return "no such value";
    }
}
