package Windows;

import Utils.Geometry.Coord;
import Foundation.Frame;
import WindowElements.WindowElement;
import WindowElements.Button;

import java.util.ArrayList;
/**
 *
 * @author Nuriklan
 */
public class MainMenuWindow extends Window {
    
    private MainWindow mainWindow;
    
    public MainMenuWindow(Frame parent){
        super(new Coord(0, 0), new Coord(1000, 1000), parent);
        
        setElements();
    }
    
    public void setElements(){
        ArrayList<WindowElement> windowElements = getWindowElements();
        windowElements.clear();
        
        Button playButton = new Button(new Coord(10, 10), new Coord(150, 30), "Start", this){
            @Override
            public void click(Coord point){
                mainWindow = new MainWindow(new Coord(0, 0), new Coord(1000, 1000), getParent().getParent());
                getParent().getParent().removeWindow(getParent().getId());
                addWindow(mainWindow);
                
                        }
        };
        windowElements.add(playButton);
        Button settingButton = new Button(new Coord(10, 60), new Coord(150, 30), "Settings", this);
        windowElements.add(settingButton);
        
        
        
    }
    
}
