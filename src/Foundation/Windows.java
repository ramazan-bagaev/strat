package Foundation;

import CharacterShape.Font;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Windows {

    private Camera camera;
    private Input input;
    private MainWindow mainWindow;

    private HashMap<String, Window> specialWindows;
    private LinkedList<Window> windows;
    private ArrayList<Font> fonts;
    private int currentId;

    public Windows(ArrayList<Font> fonts){
        currentId = 0;
        specialWindows = new HashMap<>();
        windows = new LinkedList<>();
        this.fonts = fonts;
        camera = new Camera(0, 0, 1000, 1000);
        input = new Input(this);
        this.mainWindow =  new MainWindow(new Coord(0, 0), new Coord(1000, 1000), this);
        addWindow(mainWindow);
    }

    public void addWindow(Window window){
        window.setId(getNewId());
        windows.add(window);
    }


    public void removeWindow(int id){
        Iterator<Window> it = windows.iterator();
        while (it.hasNext()){
            Window win = it.next();
            if (win.getId() == id)
            {
                for(Integer sucId: win.getWindowId()) removeWindow(sucId);
                windows.remove(win);
                removeSpecialWindow(win);
                return;
            }
        }
    }

    public void removeSpecialWindow(Window window){
        Iterator i = specialWindows.entrySet().iterator();
        while (i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            if (pair.getValue().equals(window)){
                i.remove();
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

    public void run(){
        for(Window window: windows){
            window.run();
        }
    }

    public void draw(OpenGLBinder openGLBinder){
        for(Window window: windows){
            window.draw(openGLBinder);
        }
    }


    public Camera getCamera() {
        return camera;
    }


    public MainWindow getMainWindow(){
        for(Window window: windows){
            if (window.getClass() == MainWindow.class){
                return (MainWindow)window;
            }
        }
        return null;
    }

    public Input getInput(){
        return input;
    }

    public GameEngine getGameEngine() {
        return mainWindow.getGameWindowElement().getGameEngine();
    }

    public void takeOnTop(Window window){
        windows.remove(window);
        windows.add(window);
    }

    public void addSpecialWindow(String string, Window window){
        Window sWindow = specialWindows.getOrDefault(string, null);
        if (sWindow != null){
            specialWindows.put(string, window);
            removeWindow(sWindow.getId());
            addWindow(window);
        }
        else {
            specialWindows.put(string, window);
            addWindow(window);
        }
    }
}
