package Foundation;

public class PopulationGroup extends Broadcaster {


    public static final int POOR = 0;
    public static final int MIDDLE_CLASS = 1;
    public static final int HIGH_CLASS = 2;

    public static final int NO_WORK = -1;

    public static final int NO_CITY = -1;





    public int cityId; // living
    public int wealth;
    public int workId; //  local, two works in different city can have similar id

    private int amount;

    public PopulationGroup(){
        cityId = NO_CITY;
        wealth = MIDDLE_CLASS;
        workId = NO_WORK;
        amount = 0;
    }

    public PopulationGroup(int amount){
        cityId = NO_CITY;
        wealth = MIDDLE_CLASS;
        workId = NO_WORK;
        setAmount(amount);
    }

    public PopulationGroup(int amount, PopulationGroup populationGroup){
        cityId = populationGroup.cityId;
        wealth = populationGroup.wealth;
        workId = populationGroup.workId;
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

    @Override
    public String getValue(String key) {
        switch (key){
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
