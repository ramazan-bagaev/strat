package Foundation.Person;

import Foundation.Field;

public class Person{

    private Field field;

    public Person(Field field){
        this.field = field;
        field.addPerson(this);
    }



}
