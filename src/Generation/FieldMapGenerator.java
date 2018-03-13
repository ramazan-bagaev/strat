package Generation;

import Foundation.*;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class FieldMapGenerator {

    private int number;
    private int size;
    final Random random = new Random();
    private FieldMap map;
    private Time time;
    private int[][] info;

    private ArrayList<ArrayList<Coord>> continents;
    private ArrayList<ArrayList<Coord>> mountains;

    public FieldMapGenerator(){
        number = 0;
        size = 0;
        continents = new ArrayList<>();
        mountains = new ArrayList<>();
    }

    public FieldMap generate(int number, int size, int superFieldSize, GameWindowElement gameWindowElement)
    {
        map = new FieldMap(superFieldSize, size, gameWindowElement);
        time = new Time();
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
        return map;
    }

    private void fillWithWater(){
        for(int i = 0; i < number; i++){
            for(int j = 0; j < number; j++){
                int x = i * size;
                int y = j * size;
                Field field = new Field(new Coord(i, j), new Coord(x, y), random, map, time, Ground.GroundType.Water);
                map.addField(new Coord(i, j), field);
                info[i][j] = 0;
            }
        }
    }

    private void addContinents(int cNumber, int charactSize){
        for (int cnt = 0; cnt < cNumber; cnt++) {
            ArrayList<Coord> continent = new ArrayList<>();
            LinkedList<Coord> que = new LinkedList<>();
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

            que.push(new Coord(x, y));
            int iter = 0;
            while (iter < charactSize) {
                if (que.size() == 0) break;
                Coord c = que.pop();
                Field field = new Field(new Coord(c), new Coord(c.x * size, c.y * size), random, map, time, Ground.GroundType.Soil);
                map.addField(c, field);
                continent.add(c);
                info[c.y][c.x] = cnt + 1;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        Coord lc = new Coord(i + c.x, j + c.y);
                        if (lc.x > -1 && lc.x < number && lc.y > -1 && lc.y < number) {
                            if (info[lc.y][lc.x] == 0) info[lc.y][lc.x] = -(cnt+1);
                        }
                    }
                }

                LinkedList<Coord> local = new LinkedList<>();
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i != 0 && j != 0) continue;
                        Coord lc = new Coord(i + c.x, j + c.y);
                        if (lc.x > -1 && lc.x < number && lc.y > -1 && lc.y < number) {
                            if (info[lc.y][lc.x] == 0 || info[lc.y][lc.x] == -(cnt + 1)) local.push(lc);
                        }
                    }
                }

                int num = local.size();
                for (int i = 0; i < num; i++) {
                    int rand = random.nextInt(local.size());
                    Coord newC = local.get(rand);
                    local.remove(rand);
                    que.push(newC);
                }
                iter++;
            }
            continents.add(continent);
        }
    }

    private void addMountains(){
        for(ArrayList<Coord> continent: continents){
            ArrayList<Coord> mountain = new ArrayList<>();
            int size = continent.size();
            int number = random.nextInt(size/4 + 1);

            int iter = 0;
            int banchNum = random.nextInt(size/4 + 1);

            while(iter < number){
                LinkedList<Coord> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Coord pos = continent.get(random.nextInt(size));
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
                    Coord c = que.pop();
                    Field field = new Field(new Coord(c), new Coord(c.x * this.size, c.y * this.size), random, map, time, Ground.GroundType.Rock);
                    map.addField(c, field);
                    mountain.add(c);
                    info[c.y][c.x] = continents.size() + 1;

                    LinkedList<Coord> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            Coord lc = new Coord(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0) local.push(lc);
                            }
                        }
                    }

                    int num = local.size();
                    for (int i = 0; i < num; i++) {
                        int rand = random.nextInt(local.size());
                        Coord newC = local.get(rand);
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
        for(ArrayList<Coord> continent: continents){

            int size = continent.size();
            int number = random.nextInt(size/4 + 1);

            int iter = 0;
            int banchNum = random.nextInt(size/4 + 1);

            while(iter < number){
                LinkedList<Coord> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Coord pos = continent.get(random.nextInt(size));
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
                    Coord c = que.pop();
                    Field field = new Field(new Coord(c), new Coord(c.x * this.size, c.y * this.size), random, map, time, Ground.GroundType.Sand);
                    map.addField(c, field);
                    info[c.y][c.x] = continents.size() + 2;

                    LinkedList<Coord> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            Coord lc = new Coord(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0) local.push(lc);
                            }
                        }
                    }

                    int num = local.size();
                    for (int i = 0; i < num; i++) {
                        int rand = random.nextInt(local.size());
                        Coord newC = local.get(rand);
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
        for (ArrayList<Coord> continent: continents) {
            int number = random.nextInt(continent.size()/20 + 1);
            int banchNum = random.nextInt(continent.size()/20 + 1);
            for(int k = 0; k < banchNum; k++) {
                if (number <= 0) break;
                LinkedList<Coord> river = new LinkedList<>();
                HashMap<Coord, Integer> depth = new HashMap<Coord, Integer>();

                int[][] addit = new int[this.number][this.number];
                for (int i = 0; i < this.number; i++) {
                    for (int j = 0; j < this.number; j++) {
                        addit[i][j] = info[i][j];
                    }
                }

                LinkedList<Coord> que = new LinkedList<>();
                int inc = 0;
                while (inc < continent.size()) {
                    //int index = random.nextInt(continent.size());
                    //Coord c = continent.get(index);
                    ArrayList<Coord> mountain = mountains.get(countNum);
                    if (mountain.size() == 0) break;
                    Coord c = mountain.get(random.nextInt(mountain.size()));
                    ArrayList<Coord> cs = new ArrayList<>();
                    ArrayList<Coord> nc = new ArrayList<>();
                    HashMap<Coord, Boolean> hist = new HashMap<Coord, Boolean>();
                    int mountIndex = 0;
                    while(mountIndex < mountain.size()){
                        mountIndex++;
                        for (int i = -1; i < 2; i++) {
                            for (int j = -1; j < 2; j++) {
                                if (i != 0 && j != 0) continue;
                                if (i == 0 && j == 0) continue;
                                Coord lc = new Coord(i + c.x, j + c.y);
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
                    Coord c = que.pop();
                    if (info[c.y][c.x] <= 0 || info[c.y][c.x] == continents.size() + 3) {
                        int incr = 0;
                        Coord riverC = new Coord(c);
                        int minDep = depth.get(c);
                        while(incr < continent.size()){
                            /*for(int ii = 0; ii < number; ii++){
                                for(int jj = 0; jj < number; jj++){
                                    System.out.print(depth.getOrDefault(new Coord(jj, ii), -1) + " ");
                                }
                                System.out.println();
                            }
                            System.out.println("********************************");*/
                            incr++;
                            int cur = depth.get(riverC);
                            int nextCur = cur;
                            ArrayList<Coord> randList = new ArrayList<>();
                            for (int i = -1; i < 2; i++) {
                                for (int j = -1; j < 2; j++) {
                                    if (i != 0 && j != 0) continue;
                                    if (i == 0 && j == 0) continue;
                                    Coord lc = new Coord(i + riverC.x, j + riverC.y);
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

                    //LinkedList<Coord> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            if (i == 0 && j == 0) continue;
                            Coord lc = new Coord(i + c.x, j + c.y);
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
                    //addit[c.y][c.x] = continents.size() + 3;
                }
            }
            countNum++;
        }




    }

    public void addRiver(LinkedList<Coord> river, Coord end){
        System.out.println("here");
        if (river.size() == 0) return;
        Field field = map.getFieldByIndex(river.get(0));
        River.Side out, in;
        Coord next = new Coord(end);
        if (river.size() != 1) next = river.get(1);
        out = River.convert(river.get(0).whatDirection(next));

        River riv = new River(time, map, field, out);
        field.setRiver(riv);

        for (int i = 1; i < river.size() - 1; i++) {
            field = map.getFieldByIndex(river.get(i));
            in = River.convert(river.get(i).whatDirection(river.get(i - 1)));
            out = River.convert(river.get(i).whatDirection(river.get(i + 1)));
            riv = new River(time, map, field, in, out, River.RiverType.Middle);
            field.setRiver(riv);
        }
        if (river.size() > 1) {
            field = map.getFieldByIndex(river.get(river.size() - 1));
            in = River.convert(river.get(river.size() - 1).whatDirection(river.get(river.size() - 2)));
            out = River.convert(river.get(river.size() - 1).whatDirection(end));
            riv = new River(time, map, field, in, out, River.RiverType.End);
            field.setRiver(riv);
        }
        field = map.getFieldByIndex(end);
        riv = field.getRiver();
        if (riv != null){
            in = River.convert(end.whatDirection(river.get(river.size() - 1)));
            riv.addInStream(in);
        }

        for (Coord cord : river) {
            info[cord.y][cord.x] = continents.size() + 3;
        }

    }

    public void addSwamps(){
        for(ArrayList<Coord> continent: continents){

            int size = continent.size();
            int number = random.nextInt(size/4 + 1);

            int iter = 0;
            int banchNum = random.nextInt(size/4 + 1);

            while(iter < number){
                LinkedList<Coord> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Coord pos = continent.get(random.nextInt(size));
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
                    Coord c = que.pop();
                    Field field = new Field(new Coord(c), new Coord(c.x * this.size, c.y * this.size), random, map, time, Ground.GroundType.Mud);
                    map.addField(c, field);
                    info[c.y][c.x] = continents.size() + 4;

                    LinkedList<Coord> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            Coord lc = new Coord(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0) local.push(lc);
                            }
                        }
                    }

                    int num = local.size();
                    for (int i = 0; i < num; i++) {
                        int rand = random.nextInt(local.size());
                        Coord newC = local.get(rand);
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
        for(ArrayList<Coord> continent: continents){

            int size = continent.size();
            int number = random.nextInt(size/4);

            int iter = 0;
            int banchNum = random.nextInt(size/4);

            while(iter < number){
                LinkedList<Coord> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Coord pos = continent.get(random.nextInt(size));
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
                    Coord c = que.pop();
                    Field field = map.getFieldByIndex(c);
                    field.setTree(new Tree(time, map, field));
                    info[c.y][c.x] = continents.size() + 5;

                    LinkedList<Coord> local = new LinkedList<>();
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            Coord lc = new Coord(i + c.x, j + c.y);
                            if (lc.x > -1 && lc.x < this.number && lc.y > -1 && lc.y < this.number) {
                                if (info[lc.y][lc.x] <= continents.size() && info[lc.y][lc.x] > 0) local.push(lc);
                            }
                        }
                    }

                    int num = local.size();
                    for (int i = 0; i < num; i++) {
                        int rand = random.nextInt(local.size());
                        Coord newC = local.get(rand);
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


}
