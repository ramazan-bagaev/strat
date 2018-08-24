package Foundation.Works;

import Foundation.Person.People;
import Foundation.Time.TimeDuration;
import Foundation.Works.Occupation.Occupation;

public class StewardWork extends Work {

    public StewardWork(Occupation occupation) {
        super(occupation);
    }

    @Override
    protected boolean doMainWork() {
        return false;
    }
}
