package Foundation.Person;

import Foundation.Broadcaster;
import Foundation.Field;

public class Person extends Broadcaster{

    private Field field;
    private String name;

    private Kasta kasta;

    public enum Kasta{
        Low, Middle, High
    }

    public Person(String name, Field field, Kasta kasta){
        this.field = field;
        this.name = name;
        this.kasta = kasta;
    }



    public String getName() {
        return name;
    }


    public Kasta getKasta() {
        return kasta;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }


    @Override
    public String getValue(String key) {
        switch (key){
            case "name":
                return name;
            case "kasta":
                switch (kasta){

                    case Low:
                        return "low";
                    case Middle:
                        return "middle";
                    case High:
                        return "high";
                }
        }
        return noResult;
    }
}
