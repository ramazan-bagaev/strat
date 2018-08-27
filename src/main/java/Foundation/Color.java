package Foundation;

public class Color {

    public float r;
    public float g;
    public float b;
    public float a;

    public enum Type {
        White, Black, Red, Green, Blue, Yellow, Green2, Gray, LightGray, Green3, Yellow2, Brown, Snow,
        Copper, Carbon, Oxygen, Silicon, Iron, Silver, Gold, Lead, Aluminium
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
            case Snow:
                r = 0.90f;
                g = 0.90f;
                b = 0.90f;
                break;
            case Copper:
                r = 0.85f;
                g = 0.56f;
                b = 0.34f;
                break;
            case Carbon:
                r = 0.16f;
                g = 0.17f;
                b = 0.20f;
                break;
            case Oxygen:
                r = 1f;
                g = 1f;
                b = 1f;
                break;
            case Silicon:
                r = 0.95f;
                g = 0.64f;
                b = 0.36f;
                break;
            case Iron:
                r = 0.79f;
                g = 0.80f;
                b = 0.80f;
                break;
            case Silver:
                r = 0.76f;
                g = 0.77f;
                b = 0.80f;
                break;
            case Gold:
                r = 0.1f;
                g = 0.84f;
                b = 0f;
                break;
            case Lead:
                r = 0.52f;
                g = 0.45f;
                b = 0.35f;
                break;
            case Aluminium:
                r = 0.52f;
                g = 0.53f;
                b = 0.54f;
                break;
        }
    }

    public double getGrayScale(){
        return 0.2126*r + 0.7152*g + 0.0722*b;
    }

    public void moreGreen(int times){
        g -= times*0.02f;
    }

    public void moreBlue(int times){
        b -= times*0.02f;
    }

    public void moreRed(int times){
        r -= times*0.02f;
    }
}
