package Foundation.GameWindowHelper;

import Foundation.*;
import Foundation.BasicShapes.BasicShape;
import Foundation.GameWindowHelper.FieldObjectHelperElements.InFieldHelperElement;
import Utils.Geometry.Coord;
import Utils.Geometry.Index;
import WindowElements.GameWindowElements.GameWindowHelperElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class HelperFieldMap {

    private HashMap<Index, HelperField> map;
    private int fieldSize;



    private FieldMap fieldMap;
    private GameWindowHelperElement parent;

    public HelperFieldMap(FieldMap fieldMap, GameWindowHelperElement parent){
        map = new HashMap<>();
        this.fieldSize = fieldMap.getFieldSize();
        this.fieldMap = fieldMap;
        this.parent = parent;
    }


    public HelperField getFieldByIndex(Index index){
        return map.getOrDefault(index, null);
    }

    public HelperField getFieldByPos(Coord pos){
        return map.getOrDefault(new Index((int)(pos.x/fieldSize), (int)(pos.y/fieldSize)), null);
    }

    public ArrayList<BasicShape> getShapes(Index index, Index number, MainWindowCameraConfiguration.Mode mode){
        ArrayList<BasicShape> result = new ArrayList<>();
        Index iter = new Index(0, 0);
        if ( mode == MainWindowCameraConfiguration.Mode.Detailed){
            for (int i = index.x; i <= number.x + index.x; i++) {
                for (int j = index.y; j <= number.y + index.y; j++) {
                    iter.x = i;
                    iter.y = j;
                    HelperField field = getFieldByIndex(iter);
                    if (field == null) continue;
                    result.addAll(field.getShapes());

                    ArrayList<InFieldHelperElement> inFieldHelpers = field.getInFieldHelperElements();
                    for(InFieldHelperElement helper: inFieldHelpers){
                        result.addAll(helper.getBasicShapes());
                    }
                }
            }
        }
        if (mode == MainWindowCameraConfiguration.Mode.Normal) {
            for (int i = index.x; i <= number.x + index.x; i++) {
                for (int j = index.y; j <= number.y + index.y; j++) {
                    iter.x = i;
                    iter.y = j;
                    HelperField field = getFieldByIndex(iter);
                    if (field == null) continue;
                    result.addAll(field.getShapes());
                }
            }
        }
        if (mode == MainWindowCameraConfiguration.Mode.SuperBlock){

        }

        return result;
    }


    public GameWindowHelperElement getParent() {
        return parent;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public FieldMap getFieldMap() {
        return fieldMap;
    }

    public void addByIndex(Index index, HelperField helperField){
        map.put(index, helperField);
    }

    public void addByPos(Index pos, HelperField helperField){
        map.put(new Index(pos.x/fieldSize, pos.y/fieldSize), helperField);
    }

    public Collection<HelperField> getValues(){
        return map.values();
    }

    public void drawDynamicDrawable(Index index, Index number, OpenGLBinder openGLBinder){
        Index iter = new Index(0, 0);
        for (int i = index.x; i <= number.x + index.x; i++) {
            for (int j = index.y; j <= number.y + index.y; j++) {
                iter.x = i;
                iter.y = j;
                HelperField field = getFieldByIndex(iter);
                if (field == null) continue;
                field.drawDynamicDrawable(openGLBinder);
            }
        }
    }
}
