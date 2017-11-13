public class ResourceBank {

    private Resource.Type type; // what kind of resources it can store
    private int capacity; //

    public ResourceBank(Resource.Type type){

    }

    public ResourceBank(Resource.Type type, int capacity){
        setCapacity(capacity);
        setType(type);
    }

    public Resource.Type getType() {
        return type;
    }

    public void setType(Resource.Type type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Resource getResource(int amount){
        if (amount > capacity) amount = capacity;
        if (amount < 0) amount = 0;
        Resource resource = new Resource(getType(), amount);
        setCapacity(getCapacity() - amount);
        return resource;
    }

    public void addResource(Resource resource){
        if (resource.getType() != getType()) return;
        int amount = resource.getAmount();
        setCapacity(getCapacity() + amount);
        resource.setAmount(0);
    }

    public Resource getAll(){
        Resource resource = getResource(getCapacity());
        return  resource;
    }
}
