package Foundation.Works.Occupation;

import Foundation.FieldObjects.BuildingObject.PeasantHouseObject;
import Foundation.FieldObjects.NaturalObjects.CropFieldObject;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Field;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Works.MeatMakingWork;
import Foundation.Works.VegetableMakingWork;
import Foundation.Works.WheatMakingWork;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class PeasantHouseOccupation extends Occupation {

    private CropFieldObject cropFieldObject;

    private MeatMakingWork meatMakingWork;
    private WheatMakingWork wheatMakingWork;
    private VegetableMakingWork vegetableMakingWork;

    private PeasantHouseObject peasantHouse;

    public PeasantHouseOccupation(PeasantHouseObject peasantHouse, CropFieldObject cropFieldObject){
        super(peasantHouse.getParent().getMap().getGameEngine());
        this.peasantHouse = peasantHouse;
        this.cropFieldObject = cropFieldObject;
        initWorks();
        gameEngine.addRunEntity(this);
    }

    public void initWorks(){
        Index size = peasantHouse.getSize();
        Field parent = peasantHouse.getParent();
        People people = peasantHouse.getPeople();
        ProductStore store = peasantHouse.getStore();
        Random random = parent.getRandom();

        meatMakingWork = new MeatMakingWork(store, this);
        wheatMakingWork = new WheatMakingWork(store, cropFieldObject, this);
        vegetableMakingWork = new VegetableMakingWork(store, this);
        for(Person person: people.getPersonArray()){
            int rand = random.nextInt(3);
            switch (rand){
                case 0:
                    TimeDuration timeDuration = person.getSchedule().getFreeWeekTimeDuration(49);
                    if (timeDuration == null) break;
                    meatMakingWork.addPerson(person, timeDuration);
                case 1:
                    timeDuration = person.getSchedule().getFreeWeekTimeDuration(49);
                    if (timeDuration == null) break;
                    wheatMakingWork.addPerson(person, timeDuration);
                case 2:
                    timeDuration = person.getSchedule().getFreeWeekTimeDuration(49);
                    if (timeDuration == null) break;
                    vegetableMakingWork.addPerson(person, timeDuration);
            }
        }

        linkWorks();
    }

    private void linkWorks(){
        if (meatMakingWork.getPeople().getAmount() != 0){
            addWork(meatMakingWork);
        }
        if (wheatMakingWork.getPeople().getAmount() != 0){
            addWork(wheatMakingWork);
        }
        if (vegetableMakingWork.getPeople().getAmount() != 0){
            addWork(vegetableMakingWork);
        }

    }

}
