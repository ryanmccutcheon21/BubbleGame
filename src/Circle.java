import java.awt.*;

class Circle {
    private int x, y, originalX, originalY;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
        this.originalX = x + 35;
        this.originalY = y + 35;
    }
    public void drawCircle(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x, y, 70, 70);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getOriginalX(){
        return originalX;
    }
    public int getOriginalY(){
        return originalY;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}