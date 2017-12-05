package Foundation;

public abstract class ResourceCause extends Broadcaster {

    protected Resource.Type type; // what kind of resource have


    public ResourceCause(Resource.Type type){
        this.type = type;
    }

    public abstract void renew();


    public Resource.Type getType() {
        return type;
    }

    public abstract int consume(int amount);

    @Override
    public String getValue(String key) {
        switch (key){
            case "type":
                switch (type){

                    case Tree:
                        return "tree";
                    case Stone:
                        return "stone";
                    case HumanHour:
                        return "humanHour";
                    case Food:
                        return "food";
                    case Fertility:
                        return "fertility";
                }
        }
        return "no such value";
    }
}
