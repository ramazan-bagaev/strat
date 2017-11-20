package Foundation;

public class Resource {

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public enum Type{
        Tree, Stone, HumanHour, Food, Fertility
    }

    private int amount;
    private Type type;

    public Resource(Type type, int amount){
        setAmount(amount);
        setType(type);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
