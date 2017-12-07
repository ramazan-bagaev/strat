package Foundation.Resources;

public class FoodType {

    public static final int MEAT = 0;
    public static final int PLANTS = 1;

    public int group;
    public int kind;

    public FoodType(int group){
        kind = 0;
        this.group = group;
    }

    public boolean sameAs(FoodType foodType){
        return (group == foodType.group && kind == foodType.kind);
    }

}
