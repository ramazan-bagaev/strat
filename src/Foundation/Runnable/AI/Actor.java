package Foundation.Runnable.AI;

import Foundation.Person.Person;
import Foundation.Runnable.RunEntity;
import Foundation.Time;

public abstract class Actor implements RunEntity {

    protected Time time;
    protected Person actorPerson;

    public Actor(Person actorPerson, Time time) {
        this.time = time;
    }

    public abstract void makeDecision();

    @Override
    public void run() {
        makeDecision();
    }
}
