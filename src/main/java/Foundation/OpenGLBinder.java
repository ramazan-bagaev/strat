package Foundation;

import Foundation.BasicShapes.CharacterShape.CharacterShape;
import Foundation.BasicShapes.*;
import Utils.Geometry.Coord;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLBinder {

    //private Transformation transformation;
    //private ShaderProgram shaderProgram;

    public OpenGLBinder(){
       /* transformation = new Transformation();
        shaderProgram = new ShaderProgram();
        StringLoader loader = new StringLoader();
        shaderProgram.createVertexShader(loader.get("src/main/resources/shaders/vertex.vs"));
        shaderProgram.createFragmentShader(loader.get("src/main/resources/shaders/fragment.fs"));
        shaderProgram.link();

        try {
            shaderProgram.createUniform("worldMatrix");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void draw(BasicShape basicShape){
       /* Matrix4f worldMatrix = transformation.getWorldMatrix(basicShape.getFieldPos(), basicShape.getRotation(), basicShape.getScale());
        shaderProgram.bind();
        shaderProgram.setUniform("worldMatrix", worldMatrix);*/
        RectangleShape rect;
        Color color = basicShape.getColor();
        double x;
        double y;
        double x1;
        double y1;
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

                if (rect.isFilled()) {
                    glBegin(GL_TRIANGLE_STRIP);
                    glVertex3d(x, y, 0);
                    glVertex3d(x, y1, 0);
                    glVertex3d(x1, y, 0);
                    glVertex3d(x1, y1, 0);
                    glEnd();
                    glFlush();
                    glColor3f(0, 0, 0);
                }

                if (rect.isBoxes()) {
                    glBegin(GL_LINE_LOOP);
                    glVertex3d(x, y, 1);
                    glVertex3d(x, y1, 1);
                    glVertex3d(x1, y1, 1);
                    glVertex3d(x1, y, 1);
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
                    glVertex3d(pos.x + from.x * (size.x / 100), pos.y + from.y * (size.y / 100), 1);
                    glVertex3d(pos.x + to.x * (size.x / 100), pos.y + to.y * (size.y / 100), 1);
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
                glVertex3d(posA.x, posA.y, 0);
                glVertex3d(posB.x, posB.y, 0);
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
                glVertex3d(posA.x, posA.y, 0);
                glVertex3d(posB.x, posB.y, 0);
                glVertex3d(posC.x, posC.y, 0);
                glEnd();
                glFlush();
                break;
            case Polygon:
                PolygonShape polygonShape = (PolygonShape)basicShape;
                ArrayList<Coord> vertices = polygonShape.getVerteces();
                glColor4f(color.r, color.g, color.b, color.a);
                glBegin(GL_POLYGON);
                for(Coord vertex: vertices){
                    glVertex3d(vertex.x, vertex.y, 0);
                }
                glEnd();
                glFlush();
                break;
        }
        //shaderProgram.unbind();
    }
}
