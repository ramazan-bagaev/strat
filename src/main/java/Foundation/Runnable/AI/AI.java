package Foundation.Runnable.AI;

import Foundation.Runnable.RunEntity;

public abstract class AI implements RunEntity {

    public abstract void makeDecision();

    @Override
    public void run() {
        makeDecision();
    }
}
