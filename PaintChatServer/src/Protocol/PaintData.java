import java.awt.*;
import java.io.Serializable;

public class PaintData implements Serializable {
    int x;
    int y;
    Color color;
    int size;

    public PaintData(int x, int y, Color color, int size){
        this.x=x;
        this.y=y;
        this.color=color;
        this.size=size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}