package Foundation;

import Utils.Content;
import Utils.Subscription;

abstract public class Broadcaster {

    public static String noResult = "no such value";

    abstract public String getValue(String key);

    abstract public void subscribe(String key, Subscription subscription);

    abstract public void unsubscribe(String key, Subscription subscription);

}
