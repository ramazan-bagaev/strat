package Foundation;

import CharacterShape.Font;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.LinkedList;

public class Windows {

    private Camera camera;

    private LinkedList<Window> windows;
    private ArrayList<Font> fonts;
    private int currentId;
    private double cursorPosX;
    private double cursorPosY;

    public Windows(){
        currentId = 0;
        windows = new LinkedList<>();
        fonts = new ArrayList<>();
        camera = new Camera(0, 0, 1000, 1000);
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

    public void scroll(int yScroll){
        for(int i = windows.size() - 1; i >= 0; i--){
            int x = (int)getCursorPosX();
            int y = (int)getCursorPosY();
            if (!windows.get(i).contain(new Coord(x, y))) continue;
            windows.get(i).scroll(yScroll);
            return;
        }
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


    public double getCursorPosX() {
        return cursorPosX;
    }

    public void setCursorPosX(double cursorPosX) {
        this.cursorPosX = cursorPosX;
    }

    public double getCursorPosY() {
        return cursorPosY;
    }

    public void setCursorPosY(double cursorPosY) {
        this.cursorPosY = cursorPosY;
    }

    public Camera getCamera() {
        return camera;
    }

    public void moveGameWindow(Coord delta){
        for (Window window: windows){
            if (window.getClass() == MainWindow.class){
                MainWindow mainWindow = (MainWindow)window;
                mainWindow.moveGameWindow(delta);
                return;
            }
        }
    }
}
