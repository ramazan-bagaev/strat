package CharacterShape;

import Foundation.Coord;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class FontFactory {

    String directory = "res/fonts.xml";

    public FontFactory(){

    }

    public ArrayList<Font> getFonts(){
        ArrayList<Font> result = new ArrayList<>();
        try {

            File fXmlFile = new File(directory);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();


            NodeList fontNodes = doc.getElementsByTagName("Font");

            for(int i = 0; i < fontNodes.getLength(); i++){
                Node font = fontNodes.item(i);
                NamedNodeMap fontAttrMap = font.getAttributes();
                //System.out.print("3 +" + characterAttr + "\n");
                if (fontAttrMap == null) continue;
                String fontName = new String();
                for (int j = 0; j < fontAttrMap.getLength(); j++){
                    //System.out.print("4\n");
                    Node fontAttr = fontAttrMap.item(j);
                    if (fontAttr.getNodeName() == "name") fontName = fontAttr.getNodeValue();
                }

                Font newFont = new Font(fontName);

                NodeList characterList = font.getChildNodes();
                for (int j = 0; j < characterList.getLength(); j++) {
                    //System.out.print("1\n");
                    Node character = characterList.item(j);
                    //System.out.print("2\n");
                    //System.out.println("Name2 : " + attrNode.getNodeName());
                    //Element eCharacter = (Element) character;
                    NamedNodeMap characterAttrMap = character.getAttributes();
                    //System.out.print("3 +" + characterAttr + "\n");
                    if (characterAttrMap == null) continue;
                    String definition = new String();
                    int id = 0;
                    for (int k = 0; k < characterAttrMap.getLength(); k++){
                        //System.out.print("4\n");
                        Node characterAttr = characterAttrMap.item(k);
                        if (characterAttr.getNodeName() == "id") id = Integer.parseInt(characterAttr.getNodeValue());
                        if (characterAttr.getNodeName() == "definition") definition = characterAttr.getNodeValue();
                    }
                    CharacterShape newCharacterShape = new CharacterShape(id, definition);
                    NodeList lineList = character.getChildNodes();
                    for (int k = 0; k < lineList.getLength(); k++) {
                        Node line = lineList.item(k);
                        //Element eLine = (Element) line;
                        NamedNodeMap lineAttrMap = line.getAttributes();
                        if (lineAttrMap == null) continue;
                        int x1 = 0;
                        int y1 = 0;
                        int x2 = 0;
                        int y2 = 0;
                        for(int h = 0; h < lineAttrMap.getLength(); h++){
                            Node lineAttr = lineAttrMap.item(h);
                            if (lineAttr.getNodeName() == "x1") x1 = Integer.parseInt(lineAttr.getNodeValue());
                            if (lineAttr.getNodeName() == "y1") y1 = Integer.parseInt(lineAttr.getNodeValue());
                            if (lineAttr.getNodeName() == "x2") x2 = Integer.parseInt(lineAttr.getNodeValue());
                            if (lineAttr.getNodeName() == "y2") y2 = Integer.parseInt(lineAttr.getNodeValue());
                        }
                        newCharacterShape.addLine(new Coord(x1, y1), new Coord(x2, y2));
                    }
                    newFont.addCharacterShape(newCharacterShape);
                }
                result.add(newFont);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}