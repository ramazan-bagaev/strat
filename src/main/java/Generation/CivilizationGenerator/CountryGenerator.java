package Generation.CivilizationGenerator;

import Foundation.Elements.Ground;
import Foundation.Field;
import Foundation.Elements.City;
import Foundation.FieldMap;
import Foundation.Person.Person;
import Foundation.Runnable.Country;
import Generation.FieldMapGenerator;
import Generation.NameGenerator;
import Generation.PersonGenerator;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.Random;

public class CountryGenerator extends FieldMapGenerator{

    private PersonGenerator personGenerator;
    private NameGenerator nameGenerator;
    private Random random;
    private ArrayList<Country> countries;
    private ArrayList<Index> posForCities;
    private Index iter;


    @Override
    public void startGeneration() {
        generateCountries(4);
    }

    private void generateCountries(int number){
        for(int i = 0; i < number; i++){
            generateCountry();
        }
    }

    private void generateCountry(){
        Index posForCapital = getCityPos();
        if (posForCapital == null) return;
        City capital = generateCity(posForCapital);
        Person king = personGenerator.getPerson();
        if (capital == null) return;
        Country country = new Country(nameGenerator.generate(), king, capital);
        int number = random.nextInt(5);
        for(int i = 0; i < number; i++){
            country.addCity(generateCity(getCityPos()));
        }
        countries.add(country);
    }

    private City generateCity(Index pos){
        Field field = map.getFieldByIndex(pos);
        String name = nameGenerator.generate();
        City city = new City(name, field);
        field.setCity(city);
        renewPosForCities(pos);
        return city;
    }

    private Index getCityPos(){
        if (posForCities.size() == 0) return null;
        Index pos = posForCities.get(random.nextInt(posForCities.size()));
        posForCities.remove(pos);
        return pos;
    }

    @Override
    public void init(FieldMap map, Index size){
        this.map = map;
        this.size = size;
        this.random = map.getRandom();
        this.countries = new ArrayList<>();
        this.nameGenerator = new NameGenerator(random);
        this.personGenerator = new PersonGenerator(random);
        this.iter = new Index(0, 0);
        initPosForCities();
    }

    private void initPosForCities(){
        this.posForCities = new ArrayList<>();
        Index newIter = new Index(0, 0);
        for(int y = 0; y < size.y; y++){
            newIter.y = y;
            for(int x = 0; x < size.x; x++){
                newIter.x = x;
                if (suitsForCity(newIter)) posForCities.add(new Index(newIter));
            }
        }
    }

    private boolean suitsForCity(Index center){
        Field centerField = map.getFieldByIndex(center);
        if (centerField.getGroundType() == Ground.GroundType.Water) return false;
        //if (centerField.getGroundType() == Ground.GroundType.Sand) return false;
        for(int y = -1; y < 2; y++){
            iter.y = y + center.y;
            for(int x = -1; x < 2; x++){
                if (x != 0 && y != 0) continue;
                iter.x = x + center.x;
                Field surField = map.getFieldByIndex(iter);
                if (surField == null) continue;
                if (surField.getRiver() != null || surField.getGroundType() == Ground.GroundType.Water) return true;
            }
        }
        return false;
    }

    private void renewPosForCities(Index center){
        for(int y = -3; y < 4; y++){
            iter.y = y + center.y;
            for(int x = -3; x < 4; x++){
                iter.x = x + center.x;
                posForCities.remove(iter);
            }
        }
    }

}
