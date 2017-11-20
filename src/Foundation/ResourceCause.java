package Foundation;

public class ResourceCause {

    private Resource.Type type; // what kind of resource have
    private int capacity; // amount of resource; can be renewable
    private int maxCapacity;
    private int renewAmount;


    public ResourceCause(Resource.Type type){
        setType(type);
        setCapacity(0);
        setMaxCapacity(0);
        setRenewAmount(0);
    }

    public ResourceCause(Resource.Type type, int capacity){
        setType(type);
        setCapacity(capacity);
        setMaxCapacity(capacity);
        setRenewAmount(1);
    }

    public ResourceCause(Resource.Type type, int capacity, int maxCapacity, int renewAmount){
        setType(type);
        setCapacity(capacity);
        setMaxCapacity(maxCapacity);
        setRenewAmount(renewAmount);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public Resource getResource(int amount){
        if (amount > capacity) amount = capacity;
        if (amount < 0) amount = 0;
        Resource resource = new Resource(getType(), amount);
        setCapacity(getCapacity() - amount);
        return resource;
    }

    public void renew(){
        if (capacity >= maxCapacity) return;
        capacity += renewAmount;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getRenewAmount() {
        return renewAmount;
    }

    public void setRenewAmount(int renewAmount) {
        this.renewAmount = renewAmount;
    }

    public Resource.Type getType() {
        return type;
    }

    public void setType(Resource.Type type) {
        this.type = type;
    }

    public int consume(int amount){
        int cap = getCapacity();
        if (cap < amount) amount = cap;
        setCapacity(cap - amount);
        return amount;
    }
}
