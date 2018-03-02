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
    private final int banch = 1;

    public FieldMapGenerator(){
        number = 0;
        size = 0;
    }

    public FieldMap generate(int number, int size)
    {
        map = new FieldMap();
        time = new Time();
        this.number = number;
        this.size = size;
        fillWithWater();
        addContinents(4, number* number / 16);
        return map;
    }

    private void fillWithWater(){
        for(int i = 0; i < number; i++){
            for(int j = 0; j < number; j++){
                int x = i * size;
                int y = j * size;
                Field field = new Field(new Coord(i, j), new Coord(x, y), size, random, map, time, Ground.GroundType.Water);
                map.addField(new Coord(i, j), field);
            }
        }
    }

    private void addContinents(int cNumber, int charactSize){
        int[][] info = new int[number][number];
        for (int i = 0; i < number; i++){
            for (int j = 0; j < number; j++){
                info[i][j] = 0;
            }
        }
        for (int cnt = 0; cnt < cNumber; cnt++) {
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
                Field field = new Field(new Coord(c), new Coord(c.x * size, c.y * size), size, random, map, time, Ground.GroundType.Soil);
                map.addField(c, field);
                info[c.y][c.x] = 1;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        Coord lc = new Coord(i + c.x, j + c.y);
                        if (lc.x > -1 && lc.x < number && lc.y > -1 && lc.y < number) {
                            if (info[lc.y][lc.x] == 0) info[lc.y][lc.x] = cnt + 1;
                        }
                    }
                }

                LinkedList<Coord> local = new LinkedList<>();
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i != 0 && j != 0) continue;
                        Coord lc = new Coord(i + c.x, j + c.y);
                        if (lc.x > -1 && lc.x < number && lc.y > -1 && lc.y < number) {
                            if (info[lc.y][lc.x] == 0 || info[lc.y][lc.x] == cnt + 1) local.push(lc);
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
        }
    }
}
