import java.util.LinkedList;
import java.util.ListIterator;

public class Windows {
    LinkedList<Window> windows;

    public Windows(MainWindow mainWindow){
        windows = new LinkedList<>();
        addWindow(mainWindow);
    }

    public void addWindow(Window window){
        windows.add(window);
    }

    public void click(Coord pos){
        ListIterator li = windows.listIterator(windows.size());
        for (Window window: windows){
            if (window.contain(pos)){
                window.click(pos);
            }
        }
    }


}
