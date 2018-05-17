package Foundation.Runnable.Actors;

import Foundation.Person.Person;
import Foundation.Runnable.AI.AI;
import Foundation.Runnable.RunEntity;
import Foundation.Time;

public abstract class Actor{

    protected Person actorPerson;
    protected AI ai;

    public Actor(Person actorPerson){
        this.actorPerson = actorPerson;
    }

    public void setAi(AI ai){
        this.ai = ai;
    }
    public AI getAi(){
        return ai;
    }

}
