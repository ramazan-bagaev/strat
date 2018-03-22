package Foundation;

public class Color {

    public float r;
    public float g;
    public float b;
    public float a;

    public enum Type {
        White, Black, Red, Green, Blue, Yellow, Green2, Gray, LightGray, Green3, Yellow2, Brown
    }

    public Color(float r, float b, float g) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1;
    }

    public Color(float r, float b, float g, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(Type type, float a) {
        this.a = a;
        setColor(type);
    }

    public Color(Type type) {
        this.a = 1;
        setColor(type);
    }

    public Color(Color color) {
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;
    }

    private void setColor(Type type) {
        switch (type) {
            case White:
                r = 1;
                g = 1;
                b = 1;
                break;
            case Black:
                r = 0;
                g = 0;
                b = 0;
                break;
            case Red:
                r = 1;
                g = 0;
                b = 0;
                break;
            case Green:
                r = 0;
                g = 1;
                b = 0;
                break;
            case Blue:
                r = 0;
                g = 0;
                b = 1;
                break;
            case Yellow:
                r = 1;
                g = 1;
                b = 0;
                break;
            case Yellow2:
                r = 0.8f;
                g = 0.8f;
                b = 0;
                break;
            case Green2:
                r = 0.2f;
                g = 0.6f;
                b = 0.2f;
                break;
            case Green3:
                r = 0f;
                g = 0.5f;
                b = 0f;
                break;
            case Gray:
                r = 0.4f;
                g = 0.4f;
                b = 0.4f;
                break;
            case LightGray:
                r = 0.6f;
                g = 0.6f;
                b = 0.6f;
                break;
            case Brown:
                r = 0.54f;
                g = 0.27f;
                b = 0.07f;
                break;
        }
    }

    public Color lighter() {
        return new Color(9 * r / 10, 9 * g / 10, 9 * b / 10, a);
    }
}
