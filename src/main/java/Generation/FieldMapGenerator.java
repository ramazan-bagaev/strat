package Generation;

import Foundation.*;
import Foundation.Elements.City;
import Foundation.Elements.Ground;
import Foundation.Elements.River;
import Foundation.Elements.Tree;
import Foundation.Person.Person;
import Foundation.Runnable.Country;
import Utils.Geometry.Index;

import java.util.*;

public class FieldMapGenerator {

    private int number;
    private int size;
    private Random random;
    private FieldMap map;
    private Time time;
    private int[][] info;


    private ArrayList<ArrayList<Index>> continents;
    private ArrayList<ArrayList<Index>> mountains;
    private ArrayList<City> cities;

    public FieldMapGenerator(Random random){
        number = 0;
        size = 0;
        this.random = random;
        continents = new ArrayList<>();
        mountains = new ArrayList<>();
    }

    public FieldMap generate(int number, int size, int superFieldSize, GameEngine gameEngine)
    {
        map = new FieldMap(superFieldSize, size, gameEngine);
        time = gameEngine.getTime();
        this.number = number;
        this.size = size;
        info = new int[number][number];
        System.out.println("begin generation...");
        fillWithWater();
        System.out.println("add continents...");
        addContinents(4, number* number / 16);
        System.out.println("add mountains...");
        addMountains();
        System.out.println("add deserts...");
        addDeserts();
        System.out.println("add rivers...");
        addRivers();
        System.out.println("add swamps...");
        addSwamps();
        System.out.println("add forests...");
        addForests();
        System.out.println("add cities...");
        addCities();
        System.out.println("add countries");
        addCountries();
        return map;
    }

    private void fillWithWater(){
        for(int i = 0; i < number; i++){
            for(int j = 0; j < number; j++){
                Field field = new Field(new Index(i, j), random, map, time, Ground.GroundType.Water);
                map.addField(new Index(i, j), field);
                info[i][j] = 0;
            }
        }
    }

    private void addContinents(int cNumber, int charactSize){
        for (int cnt = 0; cnt < cNumber; cnt++) {
            ArrayList<Index> continent = new ArrayList<>();
            LinkedList<Index> que = new LinkedList<>();
            int x;
            int y;
            int count = 0;
            while (true) {
                x = random.nextInt(number);
                y = random.nextInt(number);
                if (info[y][x] == 0) break;
                if (count > size * size) return;
                count += 1;
            }

            que.push(new Index(x, y));
            int iter = 0;
            while (iter < charactSize) {
                if (que.size() == 0) break;
                Index c = que.pop();
                Field field = new Field(new Index(c), random, map, time, Ground.GroundType.Soil);
                map.addField(c, field);
                continent.add(c);
                info[c.y][c.x] = cnt + 1;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        Index lc = new Index(i + c.x, j + c.y);
                        if (lc.x > -1 && lc.x < number && lc.y > -1 && lc.y < number) {
                            if (info[lc.y][lc.x] == 0) info[lc.y][lc.x] = -(cnt+1);
                        }
                    }
                }

                LinkedList<Index> local = new LinkedList<>();
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i != 0 && j != 0) continue;
                        Index lc = new Index(i + c.x, j + c.y);
                        if (lc.x > -1 && lc.x < number && lc.y > -1 && lc.y < number) {
                            if (info[lc.y][lc.x] == 0 || info[lc.y][lc.x] == -(cnt + 1)) local.push(lc);
                        }
                    }
                }

                int num = local.size();
                for (int i = 0; i < num; i++) {
                    int rand = random.nextInt(local.size());
                    Index newC = local.get(rand);
                    local.remove(rand);
                    que.push(newC);
                }
                iter++;
            }
            continents.add(continent);
        }
    }

    private void addMountains(){
        for(ArrayList<Index> continent: continents){
            ArrayList<Index> mountain = new ArrayList<>();
            int size = continent.size();
            int number = random.nextInt(size/4 + 1);

            int iter = 0;
            int banchNum = random.nextInt(size/4 + 1);

            while(iter < number){
                LinkedList<Index> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Index pos = continent.get(random.nextInt(size));
                    if (info[pos.y][pos.x] <= continents.size()){
                        que.push(pos);
                        break;
                    }
                    inc++;
                    if (inc > 2*size) break;
                }
                if (inc > 2*size) break;

                int mountGroupNum = random.nextInt(100);
                number -= mountGroupNum;
                while (mountGroupNum > 0){
                    if (que.size() == 0) break;
                    Index c = que.pop();
                    Field field = new Field(new Index(c), random, map, time, Ground.GroundType.Rock);
                    map.addField(c, field);
                    mountain.add(c);
                    info[c.y][c.x] = continents.size() + 1;

                    LinkedList<Index> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            Index lc = new Index(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0) local.push(lc);
                            }
                        }
                    }

                    int num = local.size();
                    for (int i = 0; i < num; i++) {
                        int rand = random.nextInt(local.size());
                        Index newC = local.get(rand);
                        local.remove(rand);
                        que.push(newC);
                    }
                    iter++;
                    mountGroupNum--;
                }
                que.clear();


                banchNum--;
                if (banchNum == 0) break;
            }
            mountains.add(mountain);
        }
    }

    private void addDeserts(){
        for(ArrayList<Index> continent: continents){

            int size = continent.size();
            int number = random.nextInt(size/4 + 1);

            int iter = 0;
            int banchNum = random.nextInt(size/4 + 1);

            while(iter < number){
                LinkedList<Index> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Index pos = continent.get(random.nextInt(size));
                    if (info[pos.y][pos.x] <= continents.size()){
                        que.push(pos);
                        break;
                    }
                    inc++;
                    if (inc > 2*size) break;
                }
                if (inc > 2*size) break;

                int desertGroupNum = random.nextInt(200);
                number -= desertGroupNum;
                while (desertGroupNum > 0){
                    if (que.size() == 0) break;
                    Index c = que.pop();
                    Field field = new Field(new Index(c), random, map, time, Ground.GroundType.Sand);
                    map.addField(c, field);
                    info[c.y][c.x] = continents.size() + 2;

                    LinkedList<Index> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            Index lc = new Index(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0) local.push(lc);
                            }
                        }
                    }

                    int num = local.size();
                    for (int i = 0; i < num; i++) {
                        int rand = random.nextInt(local.size());
                        Index newC = local.get(rand);
                        local.remove(rand);
                        que.push(newC);
                    }
                    iter++;
                    desertGroupNum--;
                }
                que.clear();


                banchNum--;
                if (banchNum == 0) break;
            }
        }
    }

    public void addRivers(){
        int countNum = 0;
        for (ArrayList<Index> continent: continents) {
            int number = random.nextInt(continent.size()/20 + 1);
            int banchNum = random.nextInt(continent.size()/20 + 1);
            for(int k = 0; k < banchNum; k++) {
                if (number <= 0) break;
                LinkedList<Index> river = new LinkedList<>();
                HashMap<Index, Integer> depth = new HashMap<Index, Integer>();

                int[][] addit = new int[this.number][this.number];
                for (int i = 0; i < this.number; i++) {
                    for (int j = 0; j < this.number; j++) {
                        addit[i][j] = info[i][j];
                    }
                }

                LinkedList<Index> que = new LinkedList<>();
                int inc = 0;
                while (inc < continent.size()) {
                    //int index = random.nextInt(continent.pieceSize());
                    //Index c = continent.get(index);
                    ArrayList<Index> mountain = mountains.get(countNum);
                    if (mountain.size() == 0) break;
                    Index c = mountain.get(random.nextInt(mountain.size()));
                    ArrayList<Index> cs = new ArrayList<>();
                    ArrayList<Index> nc = new ArrayList<>();
                    HashMap<Index, Boolean> hist = new HashMap<Index, Boolean>();
                    int mountIndex = 0;
                    while(mountIndex < mountain.size()){
                        mountIndex++;
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (i != 0 && j != 0) continue;
                                if (i == 0 && j == 0) continue;
                                Index lc = new Index(i + c.x, j + c.y);
                                if (hist.getOrDefault(lc, null) == null) nc.add(lc);
                                if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                    if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0){
                                        cs.add(lc);
                                    }
                                }
                            }
                        }
                        if (cs.size() > 0){
                            c = cs.get(random.nextInt(cs.size()));
                            break;
                        }
                    }

                    if (info[c.y][c.x] <= continents.size()) {
                        que.push(c);
                        depth.put(c, 0);
                        addit[c.y][c.x] = continents.size() + 3;
                        break;
                    }
                    inc++;
                }
                inc = 0;
                while (inc < continent.size()) {
                    if (que.size() == 0) break;
                    Index c = que.pop();
                    if (info[c.y][c.x] <= 0 || info[c.y][c.x] == continents.size() + 3) {
                        int incr = 0;
                        Index riverC = new Index(c);
                        int minDep = depth.get(c);
                        while(incr < continent.size()){
                            /*for(int ii = 0; ii < number; ii++){
                                for(int jj = 0; jj < number; jj++){
                                    System.out.print(depth.getOrDefault(new Index(jj, ii), -1) + " ");
                                }
                                System.out.println();
                            }
                            System.out.println("********************************");*/
                            incr++;
                            int cur = depth.get(riverC);
                            int nextCur = cur;
                            ArrayList<Index> randList = new ArrayList<>();
                            for (int i = -1; i < 2; i++) {
                                for (int j = -1; j < 2; j++) {
                                    if (i != 0 && j != 0) continue;
                                    if (i == 0 && j == 0) continue;
                                    Index lc = new Index(i + riverC.x, j + riverC.y);
                                    if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                        if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0){
                                            int dep = depth.getOrDefault(lc, -1);
                                            if (dep == -1) continue;
                                            if (dep <= minDep + 2){
                                                randList.add(lc);
                                                if (dep < minDep) minDep = dep;
                                                /*riverC = lc;
                                                river.addFirst(lc);*/
                                                //nextCur = dep;
                                                //break;
                                            }
                                        }
                                    }
                                }
                                if (nextCur != cur) break;
                            }
                            if (randList.size() > 0){
                                riverC = randList.get(random.nextInt(randList.size()));
                                river.addFirst(riverC);
                                info[riverC.y][riverC.x] = continents.size() + 3;
                                number--;
                                nextCur = depth.get(riverC);
                            }
                            else break;
                            if (cur == 0) break;
                            //if (cur == nextCur) break;
                        }
                        addRiver(river, c);
                        break;
                    }

                    //LinkedList<Index> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            if (i == 0 && j == 0) continue;
                            Index lc = new Index(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (addit[lc.y][lc.x] <= continents.size() || info[lc.y][lc.x] == continents.size() + 3){
                                    que.addLast(lc);
                                    addit[lc.y][lc.x] = continents.size() + 3;
                                    if (depth.getOrDefault(lc, -1) < depth.get(c) + 1) depth.put(lc, depth.get(c) + 1);
                                }
                            }
                        }
                    }

                    //river.push(c);
                    //addit[c.y][c.x] = continents.pieceSize() + 3;
                }
            }
            countNum++;
        }




    }

    public void addRiver(LinkedList<Index> river, Index end){
        if (river.size() == 0) return;
        Field field = map.getFieldByIndex(river.get(0));
        Index.Direction out, in;
        Index next = new Index(end);
        if (river.size() != 1) next = river.get(1);
        out = river.get(0).whatDirection(next);

        River riv = new River(field, out);
        field.setRiver(riv);

        for (int i = 1; i < river.size() - 1; i++) {
            field = map.getFieldByIndex(river.get(i));
            in = river.get(i).whatDirection(river.get(i - 1));
            out = river.get(i).whatDirection(river.get(i + 1));
            riv = new River(field, in, out, River.RiverType.Middle);
            field.setRiver(riv);
        }
        if (river.size() > 1) {
            field = map.getFieldByIndex(river.get(river.size() - 1));
            in = river.get(river.size() - 1).whatDirection(river.get(river.size() - 2));
            out = river.get(river.size() - 1).whatDirection(end);
            riv = new River(field, in, out, River.RiverType.End);
            field.setRiver(riv);
        }
        field = map.getFieldByIndex(end);
        riv = field.getRiver();
        if (riv != null){
            in = end.whatDirection(river.get(river.size() - 1));
            riv.addInStream(in);
        }

        for (Index cord : river) {
            info[cord.y][cord.x] = continents.size() + 3;
        }

    }

    public void addSwamps(){
        for(ArrayList<Index> continent: continents){

            int size = continent.size();
            int number = random.nextInt(size/4 + 1);

            int iter = 0;
            int banchNum = random.nextInt(size/4 + 1);

            while(iter < number){
                LinkedList<Index> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Index pos = continent.get(random.nextInt(size));
                    if (info[pos.y][pos.x] <= continents.size()){
                        que.push(pos);
                        break;
                    }
                    inc++;
                    if (inc > 2*size) break;
                }
                if (inc > 2*size) break;

                int swampGroupNum = random.nextInt(200);
                number -= swampGroupNum;
                while (swampGroupNum > 0){
                    if (que.size() == 0) break;
                    Index c = que.pop();
                    Field field = new Field(new Index(c), random, map, time, Ground.GroundType.Mud);
                    map.addField(c, field);
                    info[c.y][c.x] = continents.size() + 4;

                    LinkedList<Index> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            Index lc = new Index(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0) local.push(lc);
                            }
                        }
                    }

                    int num = local.size();
                    for (int i = 0; i < num; i++) {
                        int rand = random.nextInt(local.size());
                        Index newC = local.get(rand);
                        local.remove(rand);
                        que.push(newC);
                    }
                    iter++;
                    swampGroupNum--;
                }
                que.clear();


                banchNum--;
                if (banchNum == 0) break;
            }
        }
    }

    public void addForests(){
        for(ArrayList<Index> continent: continents){

            int size = continent.size();
            int number = random.nextInt(size/4 + 1);

            int iter = 0;
            int banchNum = random.nextInt(size/4 + 1);

            while(iter < number){
                LinkedList<Index> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Index pos = continent.get(random.nextInt(size));
                    if (info[pos.y][pos.x] <= continents.size()){
                        que.push(pos);
                        break;
                    }
                    inc++;
                    if (inc > 2*size) break;
                }
                if (inc > 2*size) break;

                int forestGroupNum = random.nextInt(200);
                number -= forestGroupNum;
                while (forestGroupNum > 0){
                    if (que.size() == 0) break;
                    Index c = que.pop();
                    Field field = map.getFieldByIndex(c);
                    field.setTree(new Tree(field));
                    info[c.y][c.x] = continents.size() + 5;

                    LinkedList<Index> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            Index lc = new Index(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0) local.push(lc);
                            }
                        }
                    }

                    int num = local.size();
                    for (int i = 0; i < num; i++) {
                        int rand = random.nextInt(local.size());
                        Index newC = local.get(rand);
                        local.remove(rand);
                        que.push(newC);
                    }
                    iter++;
                    forestGroupNum--;
                }
                que.clear();


                banchNum--;
                if (banchNum == 0) break;
            }
        }
    }

    public void addCountries(){
        ArrayList<Country> countries = new ArrayList<>();
        int amount = random.nextInt(5) + 1;
        int cityAmount = cities.size();
        ArrayList<Integer> indexes = new ArrayList<>();
        indexes.add(0);
        for (int i = 0; i < amount-1; i++){
            int index = random.nextInt(cityAmount);
            boolean added = false;
            for(int j = 0; j < indexes.size(); j++){
                if (indexes.get(j) < index) continue;
                indexes.add(j, index);
                added = true;
                break;
            }
            if (added) continue;
            indexes.add(index);
        }
        indexes.add(cityAmount);
        NameGenerator nameGenerator = new NameGenerator(random);
        for(int i = 0; i < amount; i++){
            ArrayList<City> countryCities = new ArrayList<>();
            for (int j = indexes.get(i); j < indexes.get(i + 1); j++) countryCities.add(cities.get(j));
            if (countryCities.size() == 0) continue;
            City capital = countryCities.get(random.nextInt(countryCities.size()));
            Person king = new Person(nameGenerator.generate(), null, Person.Kasta.Royal);
            capital.getSociety().addPerson(king);
            Country country = new Country(nameGenerator.generate(), king, capital);
            for(City city: countryCities) country.addCity(city);
            countries.add(country);
        }
        map.getGameEngine().setCountries(countries);
    }

    public void addCities(){
        NameGenerator nameGenerator = new NameGenerator(random);
        cities = new ArrayList<>();
        for (ArrayList<Index> continent: continents){

            int size = continent.size();
            int number = random.nextInt(size/100 + 1);
            System.out.println(number + " - rand");

            int count = 0;
            while (count < continent.size()) {
                Index pos = continent.get(random.nextInt(continent.size()));
                if (info[pos.y][pos.x] > 0 && info[pos.y][pos.x] <= continents.size()) {
                    Field field = map.getFieldByIndex(pos);
                    if (field.getOwner() == null) {
                        City city = new City(nameGenerator.generate(), field);
                        cities.add(city);
                        field.setCity(city);
                        field.getMap().getGameEngine().addRunEntity(city);
                        info[pos.y][pos.x] = continents.size() + 6;
                        number--;
                    }
                }
                count++;
                if (number <= 0) break;
            }

        }
    }



}
