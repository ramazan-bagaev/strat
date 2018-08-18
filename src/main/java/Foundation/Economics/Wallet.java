package Foundation.Economics;

import Utils.Broadcaster;
import Utils.Content;
import Utils.Subscription;

public class Wallet implements Broadcaster{

    private int money;
    private Content moneyContent;

    public Wallet(){
        money = 0;
        moneyContent = new Content();
    }

    public int getAmount(){
        return money;
    }

    private void addMoney(int amount){
        if (amount <= 0) return;
        money+=amount;
        moneyContent.changed();
    }

    private boolean takeMoney(int amount){
        if (amount <= 0) return true;
        if (money < amount) return false;
        money-=amount;
        moneyContent.changed();
        return true;
    }

    @Override
    public String getValue(String key) {
        switch (key){
            case "amount": return String.valueOf(money);
        }
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "amount": moneyContent.subscribe(subscription);
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "amount": moneyContent.unsubscribe(subscription);
        }
    }
}
