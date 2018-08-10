package Generation;

import Foundation.Elements.Ground;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.Time.Time;
import Generation.GeneratedObjects.Continent;
import Utils.Geometry.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MountainGenerator {

    public static void init(FieldMap map, Index mapSize, int[][] info){
        info = new int[mapSize.y][mapSize.x];
        for(int y = 0; y < mapSize.y; y++){
            for(int x = 0; x < mapSize.x; x++){

            }
        }
    }

    public static void generate(FieldMap map, Index mapSize){
        int[][] info = null;
        init(map, mapSize, info);
        ArrayList<Continent> continents = map.getContinents();
        Random random = map.getRandom();
        Time time = map.getGameEngine().getTime();
        for(Continent continent: continents){
            ArrayList<Index> mountain = new ArrayList<>();
            int size = continent.size();
            int number = random.nextInt(size/4 + 1);

            int iter = 0;
            int banchNum = random.nextInt(size/4 + 1);

            while(iter < number){
                LinkedList<Index> que = new LinkedList<>();
                int inc = 0;
                while(true) {
                    Index pos = null;//continent.get(random.nextInt(size));
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
                            if (lc.x > -1 && lc.x < number && lc.y > -1 && lc.y < number) {
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
            //mountains.add(mountain);
        }
    }
}
