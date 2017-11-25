package Foundation;

import java.util.ArrayList;

public class WindowElementGroup {

    private ArrayList<WindowElement> windowElements;

    public WindowElementGroup(){

    }

    public ArrayList<WindowElement> getWindowElements() {
        return windowElements;
    }

    public void setWindowElements(ArrayList<WindowElement> windowElements) {
        this.windowElements = windowElements;
    }

    public void addWindowElement(WindowElement windowElement){
        windowElements.add(windowElement);
    }
}
