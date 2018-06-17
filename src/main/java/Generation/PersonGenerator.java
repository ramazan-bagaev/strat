package Generation;

import Foundation.Person.Person;

import java.util.Random;

public class PersonGenerator {

    private NameGenerator nameGenerator;
    private Random random;

    public PersonGenerator(Random random){
        this.random = random;
        nameGenerator = new NameGenerator(random);
    }

    public Person getPerson(){
        return new Person(nameGenerator.generate(), null, getKasta());
    }

    private Person.Kasta getKasta(){
        int kast = random.nextInt(3);
        if (kast == 0) return Person.Kasta.Low;
        if (kast == 1) return Person.Kasta.Middle;
        return Person.Kasta.High;
    }
}
