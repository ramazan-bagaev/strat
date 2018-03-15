package Utils;

import Foundation.Coord;
import Foundation.Elements.Ground;
import Foundation.Field;
import Foundation.FieldMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class PathFinder {

    private HashMap<Coord, Integer> map; /// hash map : Coord -> {1, 0}; if 0 - is could be used as part of path, 0 - vice versa

    private LinkedList<Coord> que;
    private HashMap<Coord, Integer> depth;
    private ArrayList<Coord> used;

    public PathFinder(FieldMap fieldMap){
        que = new LinkedList<>();
        depth = new HashMap<>();
        map = new HashMap<>();
        used = new ArrayList<>();

        initMap(fieldMap);
    }

    public PathFinder(HashMap<Coord, Integer> map){
        this.map = map;
        que = new LinkedList<>();
        depth = new HashMap<>();
        used = new ArrayList<>();
    }

    public void initMap(FieldMap fieldMap){
        for (Field field: fieldMap.getValues()){
            if (field.getGroundType() == Ground.GroundType.Rock || field.getGroundType() == Ground.GroundType.Water){
                map.put(field.getFieldMapPos(), 1);
                continue;
            }
            if (field.getArmyElement() != null){
                map.put(field.getFieldMapPos(), 1);
                continue;
            }
            map.put(field.getFieldMapPos(), 0);
        }
    }

    public LinkedList<Coord> getPath(Coord from, Coord to){
        que.clear();
        used.clear();
        depth.clear();
        LinkedList<Coord> path = new LinkedList<>();
        que.addFirst(from);
        depth.put(from, 0);
        used.add(from);
        int number = map.size();//4 * Math.abs((from.x - to.x)*(from.y - to.y));
        while(number > 0) {
            if (que.size() == 0) break;
            Coord curPos = que.pop();

            if (curPos.equals(to)){

                number = map.size();//4 * Math.abs((from.x - to.x)*(from.y - to.y));
                path.addFirst(curPos);
                while(number > 0){
                    int dep = depth.get(curPos);
                    int nextDep = dep;
                    if (dep == 0) return path;
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i != 0 && j != 0) continue;
                            if (i == 0 && j == 0) continue;
                            Coord lc = new Coord(i + curPos.x, j + curPos.y);
                            nextDep = depth.getOrDefault(lc, -1);
                            if (nextDep == dep - 1){
                                path.addFirst(lc);
                                curPos = lc;
                                break;
                            }
                        }
                        if (nextDep == dep - 1) break;
                    }


                    number--;
                }
                return null;
            }

            ArrayList<Coord> arr = new ArrayList<>();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i != 0 && j != 0) continue;
                    if (i == 0 && j == 0) continue;
                    Coord lc = new Coord(j + curPos.x, i + curPos.y);
                    if (map.getOrDefault(lc, 1) == 1) continue;
                    if (depth.getOrDefault(lc, -1) > depth.get(curPos) + 1) depth.put(lc, depth.get(curPos) + 1);
                    if (!used.contains(lc)) {
                        arr.add(lc);
                        used.add(lc);
                        depth.put(lc, depth.get(curPos) + 1);
                    }
                }
            }

            for (Coord c: arr) que.add(c);


            number--;
        }

        return null;
    }

}
