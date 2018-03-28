package Graphic;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.memAllocDouble;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;

import org.lwjgl.system.MemoryUtil;

public class Mesh {

    private final int vaoId;

    private final int vboId;
    private final int idxVboId;
    private final int colorVboId;

    private final int vertexCount;

    private final int type;

    public Mesh(double[] positions, double[] colors, int[] indices, int type) {
        DoubleBuffer verticesBuffer = null;
        DoubleBuffer colourBuffer = null;
        IntBuffer indicesBuffer = null;
        try {
            this.type = type;
            verticesBuffer = memAllocDouble(positions.length);
            vertexCount = indices.length;
            verticesBuffer.put(positions).flip();

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            vboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_DOUBLE, false, 0, 0);
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            idxVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            colorVboId = glGenBuffers();
            colourBuffer = memAllocDouble(colors.length);
            colourBuffer.put(colors).flip();
            glBindBuffer(GL_ARRAY_BUFFER, colorVboId);
            glBufferData(GL_ARRAY_BUFFER, colourBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(1, 3, GL_DOUBLE, false, 0, 0);
            glBindVertexArray(0);

        } finally {
            if (verticesBuffer  != null) {
                MemoryUtil.memFree(verticesBuffer);
                MemoryUtil.memFree(indicesBuffer);
                MemoryUtil.memFree(colourBuffer);

            }
        }
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void render() {
        // Draw the mesh
        glBindVertexArray(getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(type, getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }

    public void cleanUp() {
        glDisableVertexAttribArray(0);

        // Delete the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(vboId);
        glDeleteBuffers(idxVboId);
        glDeleteBuffers(colorVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
}
