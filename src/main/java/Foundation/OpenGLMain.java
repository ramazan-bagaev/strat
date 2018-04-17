package Foundation;

import Utils.Coord;

import Foundation.BasicShapes.CharacterShape.Font;
import Foundation.BasicShapes.CharacterShape.FontFactory;
import Utils.TimeMeasurer;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.ArrayList;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class OpenGLMain {

    // The window handle
    private long window;

    private Frame frame;
    private GameEngine gameEngine;
    private OpenGLBinder openGLBinder;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(1000, 1000, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        FontFactory fontFactory = new FontFactory();
        ArrayList<Font> fonts = fontFactory.getFonts();
        frame = new Frame(fonts);
        gameEngine = frame.getGameEngine();
        openGLBinder = new OpenGLBinder();
        Input input = frame.getInput();
        Cursor cursor = input.getCursor();
        Keyboard keyboard = input.getKeyboard();
        Controller controller = input.getController();

        DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);

        GLFWMouseButtonCallback mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (button == GLFW_MOUSE_BUTTON_1){
                    glfwGetCursorPos(window, b1, b2);
                    int x = (int) b1.get(0);
                    int y = (int) b2.get(0);
                    boolean pressed = true;
                    if (action == GLFW_RELEASE) pressed = false;
                    cursor.click(new Coord(x, y), pressed, 0);
                }
                if (button == GLFW_MOUSE_BUTTON_2){
                    glfwGetCursorPos(window, b1, b2);
                    int x = (int) b1.get(0);
                    int y = (int) b2.get(0);
                    boolean pressed = true;
                    if (action == GLFW_RELEASE) pressed = false;
                    cursor.click(new Coord(x, y), pressed, 1);
                }
            }
        };

        glfwSetMouseButtonCallback(window, mouseButtonCallback);

        GLFWScrollCallback scrollCallback = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                cursor.scroll(-(int)yoffset);
            }
        };

        glfwSetScrollCallback(window, scrollCallback);

        GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() {

            @Override
            public void invoke(long window, double xpos, double ypos) {
                cursor.posRenew(xpos, ypos);
            }
        };


        glfwSetCursorPosCallback(window, cursorPosCallback);

        GLFWKeyCallback keyCallback = new GLFWKeyCallback() {

            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == GLFW_RELEASE) return;
                switch (key){
                    case GLFW_KEY_W:
                        controller.act('w');
                        break;
                    case GLFW_KEY_S:
                        controller.act('s');
                        break;
                    case GLFW_KEY_A:
                        controller.act('a');
                        break;
                    case GLFW_KEY_D:
                        controller.act('d');
                        break;
                }
            }
        };
        glfwSetKeyCallback(window, keyCallback);


        GLFWCharCallback charCallback = new GLFWCharCallback() {

            @Override
            public void invoke(long window, int codepoint) {
                keyboard.press((char)codepoint);
            }
        };

        glfwSetCharCallback(window, charCallback);
        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        glEnable (GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glViewport(0, 0, 1000, 1000);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 1000, 1000, 0, 1, -1);
        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        int i = 0;
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            gameEngine.run();
            frame.draw(openGLBinder);
            glfwSwapBuffers(window);
            // swap the color buffers



            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new OpenGLMain().run();
    }

}