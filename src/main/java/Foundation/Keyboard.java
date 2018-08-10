package Foundation;

import WindowElements.WindowElement;

public class Keyboard {

    private Input input;
    private WindowElement activeElement;

    public Keyboard(Input input){
        this.input = input;
    }

    public void press(char c){
        if (activeElement != null){
            activeElement.characterInput(c);
        }
    }

    public void setActiveElement(WindowElement activeElement) {
        this.activeElement = activeElement;
    }

    public WindowElement getActiveElement() {
        return activeElement;
    }

    public void check(WindowElement windowElement){
        if (!windowElement.equals(activeElement)) activeElement = null;
    }
}
