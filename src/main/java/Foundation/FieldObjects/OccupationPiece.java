package Foundation.FieldObjects;

import Utils.Index;

public class OccupationPiece {

    public Index pos;
    public Index size;

    public OccupationPiece(Index pos, Index size){
        this.pos = pos;
        this.size = size;
    }

    public boolean contains(Index pos, Index size){
        return (this.pos.x <= pos.x && this.pos.y <= pos.y &&
                this.pos.x + this.size.x >= pos.x + size.x &&
                this.pos.y + this.size.y >= pos.y + size.y);

    }
}
