import java.io.Serializable;

public class PaintData implements Serializable {
    int y;
    int x;
    int w;

    public PaintData(int y, int x, int w){
        this.y=y;
        this.x=x;
        this.w=w;
    }
}