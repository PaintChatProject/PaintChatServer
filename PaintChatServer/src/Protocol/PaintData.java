import java.awt.*;
import java.io.Serializable;

public class PaintData implements Serializable {
    int x;
    int y;
    int size;

    public PaintData(int x, int y, int size){
        this.x=x;
        this.y=y;
        this.size=size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}