package Foundation.Works;

import Foundation.Person.People;
import Foundation.Works.Occupation.Occupation;

public class StewardWork extends Work {

    public StewardWork(People people, Occupation occupation) {
        super(people, occupation);
    }

    @Override
    protected void doMainWork() {
    }
}
