package Foundation.GameWindowHelper;

import Foundation.*;

import java.util.ArrayList;


public class AddWorkState extends HelperState {

    private ArrayList<CoveringFieldHelper> coveringFieldHelpers;
    private ChoosenFieldHelper choosenFieldHelper;
    private City city;


    public AddWorkState(GameWindowHelperElement gameWindowHelperElement, City city) {
        super(gameWindowHelperElement);
        this.city = city;
        coveringFieldHelpers = new ArrayList<>();
        addCoveringFieldHelpers();

    }

    public void addCoveringFieldHelpers(){
        int radius = 5;
        Coord pos = new Coord(city.getParent().getFieldMapPos());
        FieldMap fieldMap = gameWindowHelperElement.getMap().getFieldMap();
        for(int i = -radius; i <= radius; i++){
            for(int j = -radius; j <= radius; j++){
                if (i == 0 && j == 0) continue;
                Coord local = new Coord(j, i).add(pos);
                Field field = fieldMap.getFieldByIndex(local);
                if (field == null) continue;
                if (field.getCity() != null){
                    continue;
                }
                if (field.getRiver() != null){
                    addCoveringFieldHelper(local, new Color(Color.Type.Red, 0.5f));
                    continue;
                }
                if (field.getTree() != null){
                    addCoveringFieldHelper(local, new Color(Color.Type.Red, 0.5f));
                    continue;
                }
                if (field.getGroundType() != Ground.GroundType.Soil){
                    addCoveringFieldHelper(local, new Color(Color.Type.Red, 0.5f));
                    continue;
                }
                addCoveringFieldHelper(local, new Color(Color.Type.Blue, 0.5f));
            }
        }
    }

    public void addCoveringFieldHelper(Coord pos, Color color){
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(pos);
        if (helperField == null){
            helperField = new HelperField(gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(pos), gameWindowHelperElement.getMap());
            gameWindowHelperElement.getMap().addByIndex(pos, helperField);
        }
        CoveringFieldHelper coveringFieldHelper = new CoveringFieldHelper(helperField, color);
        coveringFieldHelpers.add(coveringFieldHelper);
        helperField.setCoveringFieldHelper(coveringFieldHelper);
    }

    @Override
    public void click(Coord point) {
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);
        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point);
        Farm farm = new Farm(field.getTime(), field, field.getMap());
        field.setFarm(farm);
        gameWindowHelperElement.getGameWindowElement().setShapes();
        gameWindowHelperElement.clearHelperElements();
        gameWindowHelperElement.setStandartState();
    }

    @Override
    public void click2(Coord point) {

    }

    @Override
    public void hoover(Coord point) {
        point = gameWindowHelperElement.getParent().getCameraConfiguration().transform(point);
        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByPos(point);
    }

    @Override
    public void clearHelperElements() {
        for(CoveringFieldHelper coveringFieldHelper: coveringFieldHelpers){
            coveringFieldHelper.getParentField().setCoveringFieldHelper(null);
        }
        coveringFieldHelpers.clear();
    }
}
