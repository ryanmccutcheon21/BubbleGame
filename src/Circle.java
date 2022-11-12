import java.awt.*;

class Circle {
    private int x, y, originalX, originalY, rounds;
    private int repositionInt = 50;

    public Circle(int x, int y, int rounds) {
        this.x = x;
        this.y = y;
        this.originalX = x;
        this.originalY = y;
        this.rounds = rounds;
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
    public int getRandomX(){
        // increase reposition variable by 18 every round
        if(rounds > 1){
            repositionInt = repositionInt + ((rounds - 1) * 18);
        }
        int upper = x + repositionInt;
        int lower = x - repositionInt;

        // make sure inside playing field
        if(upper > 330 && lower < 0){
            upper = 330;
            lower = 0;
        } else if (lower < 0 && upper < 330){
            lower = 0;
        } else if(lower > 0 && upper > 330){
            upper = 330;
        }
        return (int) Math.floor((Math.random() * (upper - lower)) + lower + 1);
    }
    public int getRandomY(){
        // increase reposition variable by 18 every round
        if(rounds > 1){
            repositionInt = repositionInt + ((rounds - 1) * 18);
        }
        int upper = y + repositionInt;
        int lower = y - repositionInt;

        // make sure inside playing field
        if(upper > 310 && lower < 0){
            upper = 310;
            lower = 0;
        } else if (lower < 0 && upper < 310){
            lower = 0;
        } else if(lower > 0 && upper > 310){
            upper = 310;
        }
        return (int) Math.floor((Math.random() * (upper - lower)) + lower + 1);
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}