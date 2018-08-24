package Generation.CivilizationGenerator;

import Foundation.Elements.Ground;
import Foundation.Elements.Manor;
import Foundation.Elements.Village;
import Foundation.Field;
import Foundation.Elements.City;
import Foundation.FieldMap;
import Foundation.FieldObjects.FieldObject;
import Foundation.FieldObjects.FieldObjects;
import Foundation.FieldObjects.TransportObjects.TransportNetObject;
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
    private ManorGenerator manorGenerator;
    private VillageGenerator villageGenerator;

    private PersonGenerator personGenerator;
    private NameGenerator nameGenerator;

    private Random random;
    private ArrayList<Country> countries;
    private ArrayList<Index> posForCities;
    private Index iter;
    
    //private boolean nearArea = true;
    private boolean nearArea;
    private int earthArea = 0;


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
        linkCitiesInCountry(country);
        generateManors(country);
    }

    private City generateCity(Index pos){
        City city = cityGenerator.generateCity(map.getFieldByIndex(pos));
        renewPosForCities(pos);
        //generateRoad(city);
        return city;
    }

    private void linkCitiesInCountry(Country country){
        City capital = country.getCapital();
        Index from = capital.getParent().getFirstEntryPoint();
        if (from == null) return;
        for(City city: country.getCities()){
            if (city == capital) continue;
            Index to = city.getParent().getFirstEntryPoint();
            if (to == null) continue;
            roadGenerator.generateRoad(capital.getParent(), from, city.getParent(), to);
        }
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
        this.manorGenerator = new ManorGenerator(random);
        this.villageGenerator = new VillageGenerator(random);
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
        if (centerField.getGroundType() == Ground.GroundType.Sand) return false;
        
        // Checking nearest area if there is any soil
        for(int y = -2; y < 3; y += 1){
            iter.y = y + center.y;
            for(int x = -2; x < 3; x += 1){
                if (x != 0 && y != 0) continue;
                iter.x = x + center.x;
                Field surfF = map.getFieldByIndex(iter);
                if (surfF == null) continue;
                if ((surfF.getGroundType() == Ground.GroundType.Soil || surfF.getGroundType() == Ground.GroundType.Sand) && surfF.getTree() == null){
                    nearArea = true;
                    earthArea += 1;
                }
                else{
                    nearArea = false;
                }
            }
        }
        
        for(int y = -1; y < 2; y++){
            iter.y = y + center.y;
            for(int x = -1; x < 2; x++){
                if (x != 0 && y != 0) continue;
                iter.x = x + center.x;
                Field surField = map.getFieldByIndex(iter);
                if (surField == null) continue;
                if (surField.getRiver() != null  && nearArea == true && earthArea > 1) return true;
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

    private void generateManors(Country country){
        for(Index index: country.getTerritory().getIndexArray()){
            Field field = map.getFieldByIndex(index);
            if (field == null) continue;
            if (field.getCity() != null) continue;
            if (field.getGroundType() == Ground.GroundType.Water) continue;
            if (field.getGroundType() == Ground.GroundType.Rock) continue;
            if (field.getTree() != null) continue;
            if (random.nextInt(18) < 1){
                manorGenerator.generateManor(field);
                linkManor(field.getManor());
                generateVillages(field.getManor());
            }
        }
    }

    private void linkManor(Manor manor){
        Index from = manor.getCity().getParent().getFirstEntryPoint();
        if (from == null) return;
        Index to = manor.getParent().getFirstEntryPoint();
        if (to == null) return;
        roadGenerator.generateRoad(manor.getCity().getParent(), from, manor.getParent(), to);
    }

    private void generateVillages(Manor manor){
        for(Index index: manor.getTerritory().getIndexArray()){
            if (random.nextBoolean()) continue;
            Field field = map.getFieldByIndex(index);
            if (field == null) continue;
            if (field.getCity() != null) continue;
            if (field.getGroundType() == Ground.GroundType.Water) continue;
            if (field.getGroundType() == Ground.GroundType.Rock) continue;
            if (field.getManor() != null) continue;
            villageGenerator.generateVillage(field, manor);
            linkVillage(field.getVillage());
        }
    }

    private void linkVillage(Village village){
        Index from = village.getManor().getParent().getFirstEntryPoint();
        if (from == null) return;
        Index to = village.getParent().getFirstEntryPoint();
        if (to == null) return;
        roadGenerator.generateRoad(village.getManor().getParent(), from, village.getParent(), to);
    }


}
