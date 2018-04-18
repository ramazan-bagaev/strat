package Foundation.GameWindowHelper.Modes.ArmyControllingModes;

import Foundation.Color;
import Foundation.Elements.Ground;
import Foundation.Field;
import Foundation.FieldMap;
import Foundation.GameWindowHelper.HelperElements.HooveringHelper;
import Foundation.GameWindowHelper.HelperField;
import Foundation.GameWindowHelper.Modes.Mode;
import Foundation.GameWindowHelperElement;
import Images.Image;
import Utils.Coord;
import Utils.Index;

public class ArmyHooveringMode extends Mode{

    private HooveringHelper hooveringHelper;
    private Index index;

    public ArmyHooveringMode(GameWindowHelperElement gameWindowHelperElement, Index index) {
        super(gameWindowHelperElement);
        this.index = index;
    }

    public void setIndex(Index index){
        this.index = index;
    }

    public Index getIndex() {
        return index;
    }

    @Override
    public void putHelpers() {
        if (index == null) return;
        Field field = gameWindowHelperElement.getMap().getFieldMap().getFieldByIndex(index);
        if (field == null){
            return;
        }
        HelperField helperField = gameWindowHelperElement.getMap().getFieldByIndex(index);
        if (helperField == null) {
            helperField = new HelperField(field,
                    gameWindowHelperElement.getMap());
            helperField.getMap().addByIndex(index, helperField);
        }
        Color color;
        if (field.getGroundType() == Ground.GroundType.Water || field.getGroundType() == Ground.GroundType.Rock) {
            color = new Color(Color.Type.Red, 0.5f);
            hooveringHelper = new HooveringHelper(helperField, color);
            helperField.addHelperElement(hooveringHelper);
        }
        else{
            color = new Color(Color.Type.Blue, 0.5f);
            Image image;
            int size = gameWindowHelperElement.getMap().getFieldSize();
            if (field.getArmyElement() == null){
                image = new MovementHooverImage(new Coord(size, size));
            }
            else image = new MovementHooverImage(new Coord(size, size));
            hooveringHelper = new HooveringHelper(helperField, image, color);
            helperField.addHelperElement(hooveringHelper);
        }
    }

    @Override
    public void removeHelpers() {
        if (hooveringHelper == null) return;
        HelperField helperField = hooveringHelper.getParent();
        helperField.removeHelperElement(hooveringHelper);
        if (helperField.isEmpty()) helperField.delete();
        hooveringHelper = null;
    }
}
