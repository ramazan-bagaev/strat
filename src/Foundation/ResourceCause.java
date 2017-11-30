package Foundation;

public class ResourceCause extends Broadcaster {

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

    public int consumeAll(){
        return consume(getCapacity());
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "capacity":
                return String.valueOf(capacity);
            case "maxCapacity":
                return String.valueOf(maxCapacity);
            case "renewAmount":
                return String.valueOf(renewAmount);
            case "type":
                switch (type){

                    case Tree:
                        return "tree";
                    case Stone:
                        return "stone";
                    case HumanHour:
                        return "humanHour";
                    case Food:
                        return "food";
                    case Fertility:
                        return "fertility";
                }
        }
        return "no such value";
    }
}
