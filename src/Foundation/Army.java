package Foundation;

import Images.ArmyImage;

public class Army extends Element{

    protected People people;

    public Army(People people, Time time, Field field){
        super(Type.Army, time, field);
        this.people = people;
        setShapes();
    }

    public void setShapes(){
        Coord fieldPos = new Coord(getParent().getFieldMapPos());
        fieldPos.x *= getParent().getSize();
        fieldPos.y *= getParent().getSize();
        setBasicShapes(new ArmyImage(fieldPos, new Coord(getParent().getSize(), getParent().getSize()), null).getBasicShapes());
    }

    public void move(Field field){
        if (field.getArmy() != null) return;
        getParent().setArmy(null);
        setParent(field);
        field.setArmy(this);
        field.setChanged(true);
        setShapes();
    }

    public People getPeople() {
        return people;
    }
}
