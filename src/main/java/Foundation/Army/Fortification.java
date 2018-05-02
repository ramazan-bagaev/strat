package Foundation.Army;

public class Fortification {

    public enum Type{
        Natural, Special
    }

    public Type type;
    public int condition;

    public Fortification(Type type, int condition){
        this.type = type;
        this.condition = condition;
    }

    public Type getType() {
        return type;
    }

    public int getCondition() {
        return condition;
    }

    public void damage(int damage){
        if (damage < 0) damage = 0;
        if (damage > condition) damage = condition;
        condition -= damage;
    }
}
