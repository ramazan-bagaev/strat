package Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class Content {

    private ArrayList<Subscription> subscriptions;
    private int maxId;

    public Content(){
        subscriptions = new ArrayList<>();
        maxId = 0;
    }

    public void changed(){
        for(Subscription subscription : subscriptions) subscription.changed();
    }

    public int getId(){
        int res = maxId;
        maxId++;
        return res;
    }


    public void subscribe(Subscription subscription){
        subscription.id = getId();
        for(Subscription sub: subscriptions){
            if (subscription.id == sub.id) return;
        }
        subscriptions.add(subscription);
    }

    public void unsubscribe(Subscription subscription){
        Iterator<Subscription> iter = subscriptions.iterator();
        while(iter.hasNext()){
            Subscription sub = iter.next();
            if (sub.id == subscription.id) iter.remove();
        }
    }

}
