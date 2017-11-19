import static org.lwjgl.opengl.GL11.*;

public class OpenGLBinder {

    public OpenGLBinder(){

    }

    public void draw(BasicShape basicShape){
        switch (basicShape.getType()){
            case Rectangle:
                break;
            case FilledRectangle:
                RectangleShape rect = (RectangleShape)basicShape;
                glColor3f(0, 1, 0);
                float x = rect.getX();
                float y = rect.getY();
                float x1 = x + rect.getWidth();
                float y1 = y + rect.getHeight();

                glRectf((x / 500) - 1, (y / 500) - 1, (x1/ 500) - 1, (y1 / 500) - 1);
                glFlush();
                break;
            case String:
                break;
        }
    }
}
