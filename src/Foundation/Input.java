package Foundation;

public class Input {

    private Cursor cursor;
    private Keyboard keyboard;
    private Controller controller;

    public Input(Windows windows){
        cursor = new Cursor(windows, this);
        keyboard = new Keyboard(this);
        controller = new Controller(this);
    }

    public Cursor getCursor() {
        return cursor;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Controller getController() {
        return controller;
    }
}
