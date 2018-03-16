package Foundation;

import Foundation.Runnable.RunEntity;

public abstract class Actor implements RunEntity {

    private Time time;

    public Actor(Time time) {
        this.time = time;
    }

    public abstract void makeDecision();

    @Override
    public void run() {

    }
}
