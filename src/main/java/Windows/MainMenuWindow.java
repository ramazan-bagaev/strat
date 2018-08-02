package Windows;

import Utils.Geometry.Coord;
import Foundation.Frame;
import Foundation.Window;
import Foundation.WindowElement;
import WindowElements.TimeElement;
import Foundation.Button;

import java.util.ArrayList;
/**
 *
 * @author Nuriklan
 */
public class MainMenuWindow extends Window {
    public MainMenuWindow(Frame parent){
        super(new Coord(400, 400), new Coord(400, 400), parent);
        
        setElements();
    }
    
    public void setElements(){
        ArrayList<WindowElement> windowElements = getWindowElements();
        windowElements.clear();
        
        // Добавляю кнопки меню
        Button playButton = new Button(new Coord(10, 10), new Coord(40, 40), "Start", this){
            @Override
            public void click(Coord point){
                System.out.println("You've pressed this button");
                        }
        };
        windowElements.add(playButton);
        Button settingButton = new Button(new Coord(10, 60), new Coord(40, 40), "Settings", this);
        windowElements.add(settingButton);
        //playButton.click(playButton.getPos());
        
    }
    
}
