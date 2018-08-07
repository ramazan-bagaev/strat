package Foundation.Needs;

public interface Need {

    boolean isProduct();
    boolean isApartment();
    boolean isSameNeedAs(Need need);

}
