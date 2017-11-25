package CharacterShape;

import CharacterShape.*;

import java.util.ArrayList;

public class Font {

    private ArrayList<CharacterShape> characters;
    private String name;

    public Font(String name){
        setName(name);
        characters = new ArrayList<>();
    }

    public void addCharacterShape(CharacterShape characterShape){
        characters.add(characterShape);
    }

    public CharacterShape getCharacterShapeById(int characterId){
        for(CharacterShape characterShape: characters){
            if (characterShape.getCharacterId() == characterId)
                return characterShape;
        }
        return null;
    }

    public CharacterShape getCharacterShapeByDef(String definition){
        for(CharacterShape characterShape: characters){
            if (characterShape.getDefinition().equals(definition)) {
                return new CharacterShape(characterShape);
            }
        }
        return null;
    }

    public ArrayList<CharacterShape> getCharacters() {
        return characters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
