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
        Color color = basicShape.getColor();
        float x;
        float y;
        float x1;
        float y1;
        Coord pos;
        Coord size;
        Coord posA;
        Coord posB;
        Coord posC;
        float width;
        switch (basicShape.getType()){
            case Rectangle:
                rect = (RectangleShape)basicShape;
                glColor4f(color.r, color.g, color.b, color.a);
                pos = rect.getPos();
                size = rect.getSize();
                x = pos.x;
                y = pos.y;
                x1 = x + size.x;
                y1 = y + size.y;


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
                glColor4f(color.r, color.g, color.b, color.a);
                pos = rect.getPos();
                size = rect.getSize();
                x = pos.x;
                y = pos.y;
                x1 = x + size.x;
                y1 = y + size.y;

                //glRectf(x, y, x1, y1);
                glBegin(GL_QUADS);
                glVertex3f(x, y, 0);
                glVertex3f(x , y1, 0);
                glVertex3f(x1 , y1, 0);
                glVertex3f(x1, y, 0);
                glEnd();
                glFlush();

                if (rect.isBoxes()) {
                    glColor3f(0, 0, 0);
                    glBegin(GL_LINE_LOOP);
                    glVertex3f(x, y, 1);
                    glVertex3f(x, y1, 1);
                    glVertex3f(x1, y1, 1);
                    glVertex3f(x1, y, 1);
                    glEnd();
                    glFlush();
                }
                break;
            case Character:
                CharacterShape characterShape = (CharacterShape)basicShape;
                glColor4f(color.r, color.g, color.b, color.a);
                width = glGetFloat(GL_LINE_WIDTH);
                glLineWidth(1.5f);
                pos = characterShape.getPos();
                size = characterShape.getSize();
                for (int i = 0; i < characterShape.lineNumber(); i++){
                    Coord from = characterShape.getFrom(i);
                    Coord to = characterShape.getTo(i);
                    glBegin(GL_LINES);
                    glVertex3f(pos.x + from.x * (size.x / 100f), pos.y + from.y * (size.y / 100f), 1);
                    glVertex3f(pos.x + to.x * (size.x / 100f), pos.y + to.y * (size.y / 100f), 1);
                    glEnd();
                    glFlush();
                }
                glLineWidth(width);
                break;
            case Line:
                LineShape lineShape = (LineShape)basicShape;
                glColor4f(color.r, color.g, color.b, color.a);
                width = glGetFloat(GL_LINE_WIDTH);
                glLineWidth(lineShape.getWidth());
                posA = lineShape.getPosA();
                posB = lineShape.getPosB();
                glBegin(GL_LINES);
                glVertex3f(posA.x, posA.y, 0);
                glVertex3f(posB.x, posB.y, 0);
                glEnd();
                glFlush();
                glLineWidth(width);
                break;
            case Triangle:
                TriangleShape triangleShape = (TriangleShape)basicShape;
                posA = triangleShape.getPosA();
                posB = triangleShape.getPosB();
                posC = triangleShape.getPosC();
                glBegin(GL_TRIANGLES);
                glColor4f(color.r, color.g, color.b, color.a);
                glVertex3f(posA.x, posA.y, 0);
                glVertex3f(posB.x, posB.y, 0);
                glVertex3f(posC.x, posC.y, 0);
                glEnd();
                glFlush();
        }
    }
}
