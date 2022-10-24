import java.awt.*;

class Circle {
    private int x, y;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void drawCircle(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x, y, 100, 100);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}