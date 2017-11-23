package Foundation;

public class PopulationGroup {

    public static final boolean MALE = true;
    public static final boolean FEMALE = true;

    public static final int POOR = 0;
    public static final int MIDDLE_CLASS = 1;
    public static final int HIGH_CLASS = 2;





    public boolean sex;
    public int age; // from 0 to 100
    public int cityId; // living
    public int wealth;

    private int amount;

    public PopulationGroup(){
    }

    public PopulationGroup(int amount){
        setAmount(amount);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int decreaseAmount(int delta){
        if (amount < delta){
            delta = amount;
            amount = 0;
            return delta;
        }
        if (delta < 0)
            return 0;
        amount -= delta;
        return delta;
    }

    public void increaseAmount(int delta){
        if (delta < 0) return;
        amount += delta;
    }

    public boolean sameAs(PopulationGroup group){
        if (sex != group.sex) return false;
        if (age != group.age) return false;
        if (cityId != group.cityId) return false;
        if (wealth != group.wealth) return false;
        return true;
    }

    public int getAll(){
        int delta = amount;
        amount = 0;
        return delta;
    }

    public void older(){
        age += 1;
    }
}
