package CharacterShape;

import Foundation.BasicShape;
import Foundation.Color;
import Foundation.Coord;

import java.util.ArrayList;

public class CharacterShape extends BasicShape {

    private int characterId;
    private String definition; // arbitary
    private ArrayList<Coord> from;
    private ArrayList<Coord> to;

    private Coord pos;
    private Coord size;

    public CharacterShape(CharacterShape characterShape){
        super();
        setType(Type.Character);
        setColor(new Color(characterShape.getColor()));
        setPos(new Coord(characterShape.getPos()));
        setSize(new Coord(characterShape.getSize()));
        setCharacterId(characterShape.getCharacterId());
        setDefinition(characterShape.getDefinition());
        setFrom(characterShape.getFrom());
        setTo(characterShape.getTo());
    }

    public CharacterShape(int characterId){
        super();
        setType(Type.Character);
        setColor(new Color(Color.Type.Black));
        setPos(new Coord(0, 0));
        setSize(new Coord(100, 100));
        setCharacterId(characterId);
        from = new ArrayList<>();
        to = new ArrayList<>();
    }

    public CharacterShape(int characterId, String definition){
        super();
        setType(Type.Character);
        setColor(new Color(Color.Type.Black));
        setPos(new Coord(0, 0));
        setSize(new Coord(100, 100));
        setCharacterId(characterId);
        setDefinition(definition);
        from = new ArrayList<>();
        to = new ArrayList<>();
    }

    public void addLine(Coord fromCoord, Coord toCoord){
        from.add(fromCoord);
        to.add(toCoord);
    }

    public int lineNumber(){
        return from.size();
    }

    public Coord getFrom(int i){
        return from.get(i);
    }

    public Coord getTo(int i){
        return to.get(i);
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public ArrayList<Coord> getFrom() {
        return from;
    }

    public void setFrom(ArrayList<Coord> from) {
        this.from = from;
    }

    public ArrayList<Coord> getTo() {
        return to;
    }

    public void setTo(ArrayList<Coord> to) {
        this.to = to;
    }

    public Coord getPos() {
        return pos;
    }

    public void setPos(Coord pos) {
        this.pos = pos;
    }

    public Coord getSize() {
        return size;
    }

    public void setSize(Coord size) {
        this.size = size;
    }

    public void shift(Coord shift){
        pos = pos.add(shift);
    }

    @Override
    public void changeSize(double alpha) {
        pos.x = (int) (pos.x * alpha);
        pos.y = (int) (pos.y * alpha);
        size.x = (int) (size.x * alpha);
        size.y = (int) (size.y * alpha);
    }
}
