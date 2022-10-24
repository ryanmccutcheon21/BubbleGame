import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class PlayingField extends JPanel {
    private double distance;
    private int difficultyNumCircles = 0;
    private boolean playing = false;
    private List<Circle> circles;

    public PlayingField(int numCircles) {
        // create circles array
        circles = new ArrayList<Circle> ();
        // mouse listener
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(playing){
                    // remove circles if clicked
                    for(Circle circle: circles){
                        if(findDistance(circle.getX(), circle.getY(), e.getX()-50, e.getY()-50) < 50){
                            circles.remove(circle);
                            PlayingField.this.repaint();
                        }
                    }
                    // check if inside JPanel
                } else if(e.getX() < 350 && e.getX() > 50 && e.getY() > 50 && e.getY() < 350){
                    // # circles < difficulty, add and draw
                    circles.add(new Circle(e.getX()-50, e.getY()-50));
                    PlayingField.this.repaint();
                    difficultyNumCircles++;
                    // start game if num of circles = difficulty
                    if(difficultyNumCircles == numCircles){
                        JOptionPane.showMessageDialog(null, "Are you ready to start?", "Begin", JOptionPane.INFORMATION_MESSAGE);
                        playing = true;
                    }
                    // throw error if circle outside of Panel
                } else {
                    JOptionPane.showMessageDialog(null, "The circle does not fit! Try again!", "Erorr", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
        for(Circle circle: circles){
            circle.drawCircle(g);
        }
    }
}