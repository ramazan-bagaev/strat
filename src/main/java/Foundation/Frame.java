package Foundation;

import Foundation.BasicShapes.CharacterShape.Font;
import Utils.Geometry.Coord;
import Windows.MainToolbarWindow;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Frame {

    private Coord pos;
    private Coord size;
    private Camera camera;
    private Input input;
    private MainWindow mainWindow;

    //private HashMap<Window, MainWindowCameraConfiguration.Mode> modes;

    private HashMap<String, Window> specialWindows;
    private LinkedList<Window> windows;
    private ArrayList<Font> fonts;
    private int currentId;

    private CameraConfiguration cameraConfiguration;

    public Frame(ArrayList<Font> fonts){
        pos = new Coord(0, 0);
        size = new Coord(1000, 1000);
        currentId = 0;
        specialWindows = new HashMap<>();
        //modes = new HashMap<>();
        windows = new LinkedList<>();
        this.fonts = fonts;
        cameraConfiguration = new CameraConfiguration(pos, size);
        camera = new Camera(this, cameraConfiguration);
        input = new Input(this);
        this.mainWindow =  new MainWindow(pos.add(new Coord(0, 0)), new Coord(1000, 1000), this);
        addWindow(mainWindow);
        MainToolbarWindow mainToolbarWindow = new MainToolbarWindow(this);
        addWindow(mainToolbarWindow);
    }

    public void addWindow(Window window){
        window.setId(getNewId());
        windows.add(window);
        //if (!allScale) modes.put(window, mainWindow.getCameraConfiguration().getMode());
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
                //modes.put(win, null);
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
        MainWindowCameraConfiguration.Mode mode = mainWindow.getCameraConfiguration().getMode();
        for(Window window: windows){
            //if (modes.getOrDefault(window, mode) == mode)
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

    public Coord getSize() {
        return size;
    }

    public Coord getPos() {
        return pos;
    }

    public MainWindowCameraConfiguration.Mode getMode(){
        return mainWindow.getCameraConfiguration().getMode();
    }

    //public MainWindowCameraConfiguration.Mode getModeForWindow(Window window){
    //    return modes.getOrDefault(window, null);
    //}

   // public boolean isSameModeAs(Window window){
       // MainWindowCameraConfiguration.Mode windowMode = getModeForWindow(window);
       // if (windowMode == null) return true;
        //return (getMode() == getModeForWindow(window));
   // }
}
