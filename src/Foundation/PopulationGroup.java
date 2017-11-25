package Foundation;

public class PopulationGroup {

    public static final boolean MALE = true;
    public static final boolean FEMALE = false;

    public static final int POOR = 0;
    public static final int MIDDLE_CLASS = 1;
    public static final int HIGH_CLASS = 2;

    public static final int NO_WORK = -1;

    public static final int NO_CITY = -1;

    public static final int MIN_AGE = 0;
    public static final int MAX_AGE = 100;
    public static final int MIN_WORKING_AGE = 18;
    public static final int MAX_WORKING_AGE = 60;





    public boolean sex;
    public int age; // from 0 to 100
    public int cityId; // living
    public int wealth;
    public int workId; //  local, two works in different city can have similar id

    private int amount;

    public PopulationGroup(){
        sex = MALE;
        age = MIN_WORKING_AGE;
        cityId = NO_CITY;
        wealth = MIDDLE_CLASS;
        workId = NO_WORK;
        amount = 0;
    }

    public PopulationGroup(int amount){
        sex = MALE;
        age = MIN_WORKING_AGE;
        cityId = NO_CITY;
        wealth = MIDDLE_CLASS;
        workId = NO_WORK;
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
        if (workId != group.workId) return false;
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
