package Generation;

import Foundation.Coord;
import Foundation.Field;

import java.util.ArrayList;

public class Continent{

    private ArrayList<Coord> border;

    private ArrayList<Mountain> mountains;
    private ArrayList<InnerSea> seas;
    private ArrayList<River> rivers;

    public Continent(ArrayList<Coord> border){
        this.border = border;
        mountains = new ArrayList<>();
        seas = new ArrayList<>();
        rivers = new ArrayList<>();
    }




}
