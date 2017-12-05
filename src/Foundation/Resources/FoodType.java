package Foundation.Resources;

public class FoodType {

    public static final int MEAT = 0;
    public static final int PLANTS = 1;
    public static final int WATER = 2;

    public int group;
    public int kind;

    public FoodType(int group){
        kind = 0;
        this.group = group;
    }

}
