package Foundation;

public class Cursor {

    private double posX;
    private double posY;

    private Windows windows;

    private Window activeWindow;
    private WindowElement activeWindowElement;
    private WindowElementGroup activeWindowElementGroup;

    public Cursor(){

    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Window getActiveWindow() {
        return activeWindow;
    }

    public void setActiveWindow(Window activeWindow) {
        this.activeWindow = activeWindow;
    }

    public WindowElement getActiveWindowElement() {
        return activeWindowElement;
    }

    public void setActiveWindowElement(WindowElement activeWindowElement) {
        this.activeWindowElement = activeWindowElement;
    }

    public WindowElementGroup getActiveWindowElementGroup() {
        return activeWindowElementGroup;
    }

    public void setActiveWindowElementGroup(WindowElementGroup activeWindowElementGroup) {
        this.activeWindowElementGroup = activeWindowElementGroup;
    }

    public void click(Coord point){
        if (activeWindowElement != null){
            if (activeWindowElement.contain(point)){
                return;
            }
            else activeWindowElement = null;
        }
        if (activeWindowElementGroup != null){
            if (activeWindowElementGroup.contain(point)){
                return;
            }
        }
    }
}
