package Foundation.Needs;

public interface Need {

    boolean isProduct();
    boolean isApartment();
    boolean isSameNeedAs(Need need);
    int getAmount();
    void addAmount(int amount);
    void decreaseAmount(int amount);

}
