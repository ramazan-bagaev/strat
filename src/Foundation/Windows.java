package Foundation;

import CharacterShape.Font;

import java.util.ArrayList;
import java.util.LinkedList;

public class Windows {

    private LinkedList<Window> windows;
    private ArrayList<Font> fonts;

    public Windows(){
        windows = new LinkedList<>();
        fonts = new ArrayList<>();
    }

    public void addWindow(Window window){
        windows.add(window);
    }

    public void click(Coord pos){
        for(int i = windows.size() - 1; i >= 0; i--){
            Window window = windows.get(i);
            if (window.contain(pos)){
                window.click(pos);
            }
        }
    }

    public ArrayList<BasicShape> getShapes(){
        ArrayList<BasicShape> result = new ArrayList<>();
        for (Window window: windows){
            result.addAll(window.getShapes());
        }
        return result;
    }

    public void removeWindow(Window window){
        windows.remove(window);
    }

    public boolean isOnTop(Window window){
        if (windows.size() == 0) return false;
        if (windows.get(0) == window) return true;
        return false;
    }

    public ArrayList<Font> getFonts() {
        return fonts;
    }

    public void setFonts(ArrayList<Font> fonts) {
        this.fonts = fonts;
    }

    public void addFont(Font font){
        fonts.add(font);
    }

    public Font getUsedFont(String name){
        for(Font font: fonts){
            if (font.getName().equals(name)){
                return font;
            }
        }
        return null;
    }
}
