package Foundation.Works.Occupation;

import Foundation.FieldObjects.BuildingObject.PeasantHouseObject;
import Foundation.Person.People;
import Foundation.Person.Person;
import Foundation.Field;
import Foundation.Products.ProductStore;
import Foundation.Time.TimeDuration;
import Foundation.Time.TimeDurations.PeasantWorkTimeDuration;
import Foundation.Works.MeatMakingWork;
import Foundation.Works.VegetableMakingWork;
import Foundation.Works.WheatMakingWork;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class PeasantHouseOccupation extends Occupation {

    private MeatMakingWork meatMakingWork;
    private WheatMakingWork wheatMakingWork;
    private VegetableMakingWork vegetableMakingWork;

    private PeasantHouseObject peasantHouse;

    public PeasantHouseOccupation(PeasantHouseObject peasantHouse){
        super(peasantHouse.getParent().getMap().getGameEngine());
        this.peasantHouse = peasantHouse;
        initWorks();
        gameEngine.addRunEntity(this);
    }

    public void initWorks(){
        TimeDuration timeDuration = new PeasantWorkTimeDuration(8, 20); // from 8 am to 20 pm
        Index size = peasantHouse.getSize();
        Field parent = peasantHouse.getParent();
        People people = peasantHouse.getPeople();
        ProductStore store = peasantHouse.getStore();
        int square = size.x*size.y - 1;
        Random random = parent.getRandom();
        int wheatFieldSize = random.nextInt(square + 1);
        square -= wheatFieldSize;

        int meatFieldSize = random.nextInt(square + 1);
        square -= meatFieldSize;

        int vegetableFieldSize = square;

        square = size.x*size.y - 1;
        int amount = people.getAmount();
        if (amount < 2) return;
        int wheatWorking = amount * wheatFieldSize / square;
        if (wheatWorking == 0 && wheatFieldSize != 0) wheatWorking = 1;

        int meatWorking = amount * meatFieldSize / square;
        if (meatWorking == 0 && meatFieldSize != 0) meatWorking = 1;

        int vegetableWorking = amount - wheatWorking - meatWorking;
        if (vegetableFieldSize != 0 && vegetableWorking == 0){
            if (wheatWorking > 0){
                wheatWorking -=1;
                vegetableWorking = 1;
            }
            else if (meatWorking > 0){
                meatWorking -= 1;
                vegetableWorking = 1;
            }
        }
        if (vegetableFieldSize == 0) vegetableWorking = 0;

        System.out.println("ew");
        System.out.println(wheatFieldSize + " " + wheatWorking);
        System.out.println(meatFieldSize + " " + meatWorking);
        System.out.println(vegetableFieldSize + " " + vegetableWorking);

        if (wheatWorking + meatWorking + vegetableWorking != amount) return;
        ArrayList<Person> personArray = people.getPersonArray();
        People wheatPeople = new People();
        for(int i = 0; i < wheatWorking; i++){
            wheatPeople.addPerson(personArray.get(i));
        }
        wheatMakingWork = new WheatMakingWork(store, this);
        if (meatWorking == 0) return;

        People meatPeople = new People();
        for(int i = wheatWorking; i < wheatWorking + meatWorking; i++){
            meatPeople.addPerson(personArray.get(i));
        }
        meatMakingWork = new MeatMakingWork(store, this);

        if (vegetableWorking == 0) return;

        People vegetablePeople = new People();
        for(int i = wheatWorking + meatWorking; i < wheatWorking + meatWorking + vegetableWorking; i++){
            vegetablePeople.addPerson(personArray.get(i));
        }
        vegetableMakingWork = new VegetableMakingWork(store, this);

        linkWorks();
    }

    private void linkWorks(){
        if (meatMakingWork != null){
            addWork(meatMakingWork);
        }
        if (wheatMakingWork != null){
            addWork(wheatMakingWork);
        }
        if (vegetableMakingWork != null){
            addWork(vegetableMakingWork);
        }

    }

}
