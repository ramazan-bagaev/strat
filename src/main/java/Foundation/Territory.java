package Foundation;

import Utils.*;
import Utils.Geometry.Index;

import java.util.ArrayList;

public class Territory implements Broadcaster {

    protected Content territoryContent;

    protected ArrayList<Index> territory;

    public Territory(Territory territory){
        this.territory = new ArrayList<>();
        for(Index index: territory.getIndexArray()){
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
        for(Index index: territory.getIndexArray()){
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

    public ArrayList<Index> getIndexArray(){
        return territory;
    }

    public boolean contains(Index index){
        return territory.contains(index);
    }

    public int size(){
        return territory.size();
    }

    public Territory antiIntersect(Territory otherTerritory){
        Territory result = new Territory();
        for(Index index: otherTerritory.getIndexArray()){
            if (!contains(index)) result.add(index);
        }
        return result;
    }

    public Territory intersect(Territory otherTerritory){
        Territory result = new Territory();
        for(Index index: otherTerritory.getIndexArray()){
            if (contains(index)) result.add(index);
        }
        return result;
    }

    public void addOrRemove(Territory territory){
        for(Index index: territory.getIndexArray()){
            if (this.territory.contains(index)) this.territory.remove(index);
            else this.territory.add(index);
        }
        if (territory.size() != 0) territoryContent.changed();
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
                territoryContent.unsubscribe(subscription);
                break;
        }
    }
}
