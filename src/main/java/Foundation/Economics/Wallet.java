package Foundation.Economics;

public class Wallet {

    private int money;

    public Wallet(){
        money = 0;
    }

    private void addMoney(int amount){
        if (amount <= 0) return;
        money+=amount;
    }

    private boolean takeMoney(int amount){
        if (amount <= 0) return true;
        if (money < amount) return false;
        money-=amount;
        return true;
    }

}
