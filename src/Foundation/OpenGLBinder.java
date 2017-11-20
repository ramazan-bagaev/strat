package Foundation;

import CharacterShape.CharacterShape;
import CharacterShape.Font;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLBinder {

    public OpenGLBinder(){

    }

    public void draw(BasicShape basicShape){
        RectangleShape rect;
        ArrayList<Float> color;
        float x;
        float y;
        float x1;
        float y1;
        switch (basicShape.getType()){
            case Rectangle:
                rect = (RectangleShape)basicShape;
                color = getColor(rect.getColor());
                glColor3f(color.get(0), color.get(1), color.get(2));
                x = rect.getX();
                y = rect.getY();
                x1 = rect.getX() + rect.getWidth();
                y1 = rect.getY() + rect.getHeight();


                glBegin(GL_LINE_LOOP);
                glVertex3f(x, y, 1);
                glVertex3f(x , y1, 1);
                glVertex3f(x1 , y1, 1);
                glVertex3f(x1, y, 1);
                glEnd();
                glFlush();

                break;
            case FilledRectangle:
                rect = (RectangleShape)basicShape;
                color = getColor(rect.getColor());
                glColor3f(color.get(0), color.get(1), color.get(2));
                x = rect.getX();
                y = rect.getY();
                x1 = rect.getX() + rect.getWidth();
                y1 = rect.getY() + rect.getHeight();

                //glRectf(x, y, x1, y1);
                glBegin(GL_QUADS);
                glVertex3f(x, y, 0);
                glVertex3f(x , y1, 0);
                glVertex3f(x1 , y1, 0);
                glVertex3f(x1, y, 0);
                glEnd();
                glFlush();

                glColor3f(0, 0, 0);
                glBegin(GL_LINE_LOOP);
                glVertex3f(x, y, 1);
                glVertex3f(x, y1, 1);
                glVertex3f(x1, y1, 1);
                glVertex3f(x1, y, 1);
                glEnd();
                glFlush();
                break;
            case Character:
                CharacterShape characterShape = (CharacterShape)basicShape;
                color = getColor(characterShape.getColor());
                glColor3f(color.get(0), color.get(1), color.get(2));
                Coord pos = characterShape.getPos();
                Coord size = characterShape.getSize();
                for (int i = 0; i < characterShape.lineNumber(); i++){
                    Coord from = characterShape.getFrom(i);
                    Coord to = characterShape.getTo(i);
                    glBegin(GL_LINES);
                    glVertex3f(pos.x + from.x * (size.x / 100f), pos.y + from.y * (size.y / 100f), 1);
                    glVertex3f(pos.x + to.x * (size.x / 100f), pos.y + to.y * (size.y / 100f), 1);
                    glEnd();
                    glFlush();
                }
                break;
        }
    }

    public ArrayList<Float> getColor(BasicShape.Color color){
        ArrayList<Float> result = new ArrayList<>();
        result.add(0f);
        result.add(0f);
        result.add(0f);
        switch(color){

            case White:
                result.set(0, 1f);
                result.set(1, 1f);
                result.set(2, 1f);
                break;
            case Black:
                result.set(0, 0f);
                result.set(1, 0f);
                result.set(2, 0f);
                break;
            case Red:
                result.set(0, 1f);
                result.set(1, 0f);
                result.set(2, 0f);
                break;
            case Green:
                result.set(0, 0f);
                result.set(1, 1f);
                result.set(2, 0f);
                break;
            case Blue:
                result.set(0, 0f);
                result.set(1, 0f);
                result.set(2, 1f);
                break;
            case Yellow:
                result.set(0, 1f);
                result.set(1, 1f);
                result.set(2, 0f);
                break;
            case Green2:
                result.set(0, 0.2f);
                result.set(1, 1f);
                result.set(2, 0.2f);
                break;
            case Gray:
                result.set(0, 0.4f);
                result.set(1, 0.4f);
                result.set(2, 0.4f);
                break;
            case LightGray:
                result.set(0, 0.6f);
                result.set(1, 0.6f);
                result.set(2, 0.6f);
                break;
        }
        return result;
    }
}
