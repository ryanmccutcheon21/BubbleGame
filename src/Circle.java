import java.awt.*;

class Circle {
    private int x, y, a, b;

    public Circle(int x, int y, int a, int b) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
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
    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}