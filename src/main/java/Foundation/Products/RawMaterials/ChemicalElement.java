package Foundation.Products.RawMaterials;

import java.util.ArrayList;

public class ChemicalElement {

    public enum Type{
        Copper, Carbon, Oxygen, Silicon, Iron, Silver, Gold, Lead, Aluminium
    }

    public static String getShortName(Type type){
        switch (type){

            case Copper:
                return "cu";
            case Carbon:
                return "c";
            case Oxygen:
                return "o";
            case Silicon:
                return "si";
            case Iron:
                return "fe";
            case Silver:
                return "ag";
            case Gold:
                return "au";
            case Lead:
                return "pb";
            case Aluminium:
                return "al";
        }
        return null;
    }


}
