package Foundation.Person;

import Foundation.Broadcaster;
import Foundation.Field;

public class Person extends Broadcaster{

    private Field field;
    private String name;


    private enum Kasta{
        
    }

    public Person(String name, Field field){
        this.field = field;
        this.name = name;
    }


    @Override
    public String getValue(String key) {
        switch (key){
            case "name":
                return name;
        }
        return noResult;
    }
}
