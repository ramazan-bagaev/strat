package Foundation;

import CharacterShape.Font;

import java.util.ArrayList;
import java.util.LinkedList;

public class Windows {

    private LinkedList<Window> windows;
    private ArrayList<Font> fonts;
    private int currentId;

    public Windows(){
        currentId = 0;
        windows = new LinkedList<>();
        fonts = new ArrayList<>();
    }

    public void addWindow(Window window){
        window.setId(getNewId());
        windows.add(window);
    }

    public void click(Coord pos){
        for(int i = windows.size() - 1; i >= 0; i--){
            Window window = windows.get(i);
            if (window.contain(pos)){
                window.click(pos);
                return;
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
        for (Window win: windows) {
            if (win.getId() == window.getId())
            {
                windows.remove(win);
                return;
            }
        }
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

    public LinkedList<Window> getWindows(){
        return windows;
    }

    public int getNewId(){
        int newId = currentId;
        currentId++;
        return newId;
    }
}
