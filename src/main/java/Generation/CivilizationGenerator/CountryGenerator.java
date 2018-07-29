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

    private CityGenerator cityGenerator;
    private RoadGenerator roadGenerator;

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
        map.getGameEngine().setCountries(countries);
    }

    private void generateCountry(){
        Index posForCapital = getCapitalPos();
        if (posForCapital == null) return;
        City capital = generateCity(posForCapital);
        Person king = personGenerator.getPerson();
        if (capital == null) return;
        Country country = new Country(nameGenerator.generate(), king, capital);
        int number = random.nextInt(5);
        for(int i = 0; i < number; i++){
            Index newPos = getCityPos(country);
            if (newPos == null) continue;
            country.addCity(generateCity(newPos));
        }
        countries.add(country);
    }

    private City generateCity(Index pos){
        City city = cityGenerator.generatCity(map.getFieldByIndex(pos));
        renewPosForCities(pos);
        generateRoad(city);
        return city;
    }

    private void generateRoad(City city){
        Index newPos = city.getParent().getFieldMapPos().add(new Index(random.nextInt(21) - 10, random.nextInt(21) - 10));
        roadGenerator.generateRoad(map.getFieldByIndex(newPos), new Index(50, 50), city.getParent(), new Index(50,50));
    }

    private Index getCapitalPos(){
        if (posForCities.size() == 0) return null;
        Index pos = posForCities.get(random.nextInt(posForCities.size()));
        posForCities.remove(pos);
        return pos;
    }

    private Index getCityPos(Country country){
        if (posForCities.size() == 0) return null;
        for(Index newPos: posForCities){
            if (isSuitableForCity(newPos, country)) return newPos;
        }

        return null;
    }

    private boolean isSuitableForCity(Index pos, Country country){
        for(City city: country.getCities()){
            if (city.getParent().getFieldMapPos().distance(pos) < 10) return true;
        }
        return false;
    }

    @Override
    public void init(FieldMap map, Index size){
        this.map = map;
        this.size = size;
        this.random = map.getRandom();
        this.countries = new ArrayList<>();
        this.nameGenerator = new NameGenerator(random);
        this.personGenerator = new PersonGenerator(random);
        this.roadGenerator = new RoadGenerator(map);
        this.cityGenerator = new CityGenerator(random);
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
        if (centerField.getTree() != null) return false;
        if (centerField.getGroundType() == Ground.GroundType.Rock) return false;
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
