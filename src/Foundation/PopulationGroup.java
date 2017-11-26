package Foundation;

public class PopulationGroup extends Broadcaster {

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

    @Override
    public String getValue(String key) {
        switch (key){
            case "age": return String.valueOf(age);
            case "sex":
                if (sex == MALE) return "male";
                if (sex == FEMALE) return "female";
            case "wealth":
                if (wealth == POOR) return "poor";
                if (wealth == MIDDLE_CLASS) return "middle class";
                if (wealth == HIGH_CLASS) return "high class";
            case "work":
                if (workId == NO_WORK) return "no work";
                return String.valueOf(workId);
            case "amount": return String.valueOf(amount);
        }
        return Broadcaster.noResult;
    }
}
