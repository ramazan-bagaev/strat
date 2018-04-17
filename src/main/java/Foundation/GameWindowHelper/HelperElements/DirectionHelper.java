package Foundation.GameWindowHelper.HelperElements;

import Foundation.GameWindowHelper.HelperField;
import Utils.Index;

public class DirectionHelper extends HelperElement {

    private Index.Direction from;
    private Index.Direction to;

    public DirectionHelper(HelperField helperField, Index.Direction from, Index.Direction to) {
        super(helperField);
        this.from = from;
        this.to = to;
    }
}
