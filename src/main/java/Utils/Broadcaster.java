package Utils;

import Utils.Content;
import Utils.Subscription;

public interface Broadcaster {

    String noResult = "no such value";

    String getValue(String key);

    void subscribe(String key, Subscription subscription);

    void unsubscribe(String key, Subscription subscription);

}
