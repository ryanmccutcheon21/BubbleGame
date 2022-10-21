import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PlayingField extends JPanel {
    private int circleX, circleY;
    private int width = 100;
    private int height = 100;
    private double distance;
    // private String playing;
    private int circle = 0;
    // private int numCircles;

    public PlayingField(int numCircles) {
        // this.numCircles = numCircles;
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                // if inside JPanel, draw circle
                if(e.getX() < 350 && e.getX() > 50 && e.getY() > 50 && e.getY() < 350){
                    // if painted circle < numCircles, drawCircle
                    if(circle < numCircles){
                        drawCircle(e.getX()-50, e.getY()-50);
                        circle += 1;
                    } else if(circle == numCircles) {
                        circle += 1;
                        JOptionPane.showMessageDialog(null, "Are you ready to start?", "Begin", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The circle does not fit! Try again!", "Erorr", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void drawCircle(int x, int y){
        repaint(circleX,circleY,width,height);
        circleX = x;
        circleY = y;
        repaint(circleX, circleY, width, height);
    }

    private double findDistance(int x1, int y1, int x2, int y2){
        int xDiff = x1 - x2;
        int yDiff = y1 - y2;
        // make negative differences positive
        if(xDiff < 0){
            xDiff *= -1;
        } else if(yDiff < 0){
            yDiff += -1;
        }
        // D^2 = x^2 + y^2
        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        return distance;
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(circleX != 0){
            g.setColor(Color.red);
            g.drawOval(circleX, circleY, width, height);
        }
    }
}