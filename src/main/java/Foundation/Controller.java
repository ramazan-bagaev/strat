package Foundation;

public class Controller {

    public enum Action{
        MoveLeft, MoveRight, MoveUp, MoveDown, None
    }

    private Input input;

    private Action action;

    private Window activeWindow;

    public Controller(Input input){
        this.input = input;
    }

    public void act(char character){
        if (input.getKeyboard().getActiveElement() != null) return;
        switch (character){
            case 'w':
                action = Action.MoveUp;
                break;
            case 's':
                action = Action.MoveDown;
                break;
            case 'a':
                action = Action.MoveLeft;
                break;
            case 'd':
                action = Action.MoveRight;
                break;
        }
        if (!action.equals(Action.None)){
            if (activeWindow != null) {
                activeWindow.handleAction(action);
                action = Action.None;
            }
        }
    }

    public void setActiveWindow(Window activeWindow) {
        this.activeWindow = activeWindow;
    }

}
