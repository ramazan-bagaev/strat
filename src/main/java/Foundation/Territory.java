package Foundation;

import Utils.Broadcaster;
import Utils.Content;
import Utils.Index;
import Utils.Subscription;

import java.util.ArrayList;

public class Territory implements Broadcaster {

    protected Content territoryContent;

    private ArrayList<Index> territory;

    public Territory(Territory territory){
        this.territory = new ArrayList<>();
        for(Index index: territory.getTerritory()){
            this.territory.add(index);
        }
        territoryContent = new Content();
    }

    public Territory(){
        this.territory = new ArrayList<>();
        territoryContent = new Content();
    }

    public Territory(ArrayList<Index> territory){
        this.territory = territory;
        territoryContent = new Content();
    }

    public void add(Index index){
        if (territory.contains(index)) return;
        territory.add(index);
        territoryContent.changed();
    }

    public void add(Territory territory){
        for(Index index: territory.getTerritory()){
            if (this.territory.contains(index)) continue;
            this.territory.add(index);
            this.territoryContent.changed();
        }
    }

    public void remove(Index index){
        territory.remove(index);
    }

    public void clear(){
        territory.clear();
    }

    public ArrayList<Index> getTerritory(){
        return territory;
    }

    public boolean contains(Index index){
        return territory.contains(index);
    }

    public int size(){
        return territory.size();
    }

    @Override
    public String getValue(String key) {
        return noResult;
    }

    @Override
    public void subscribe(String key, Subscription subscription) {
        switch (key){
            case "territory":
                territoryContent.subscribe(subscription);
                break;
        }
    }

    @Override
    public void unsubscribe(String key, Subscription subscription) {
        switch (key){
            case "territory":
                territoryContent.subscribe(subscription);
                break;
        }
    }
}
