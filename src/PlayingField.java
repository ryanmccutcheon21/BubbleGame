import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class PlayingField extends JPanel implements ActionListener{
    private double distance;
    private int difficultyNumCircles = 0;
    private boolean playing = false;
    private List<Circle> circles;

    // track # of circles popped
    private int circlesPopped;
    // track # of rounds
    private int rounds = 1;

    // timer delay and set timer variable
    private int delay = 1000;
    protected Timer timer;
    private int roundTime = 15;

    // reposition variables
    private int upperIntX = 50;
    private int lowerIntX = 50;
    private int upperIntY = 50;
    private int lowerIntY = 50;

    public PlayingField(int numCircles) {
        // create Timer
        timer = new Timer(delay, this);
        // create circles array
        circles = new ArrayList<Circle> ();

        // mouse listener
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(playing){
                    // remove circles if clicked
                    for(Circle circle: circles){
                        if(findDistance(circle.getX(), circle.getY(), e.getX()-35, e.getY()-35) < 35){
                            circles.remove(circle);
                            circlesPopped++;
                            PlayingField.this.repaint();
                            // end round if all circles are popped
                            if(circlesPopped % difficultyNumCircles == 0){
                                rounds++;
                                timer.stop();
                                // check if rounds = 11 to end game
                                if(rounds == 11){
                                    JOptionPane.showMessageDialog(null, "You Win!!!", "Congrats!!", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Round " + (rounds - 1) + " Over! Ready for Round " + rounds + "?", "Round " + rounds, JOptionPane.INFORMATION_MESSAGE);
                                    roundTime = 16 - rounds;
                                    for(int i = 0; i < difficultyNumCircles; i++){
                                        // random positions for circle starting position rounds 2-10
                                        circles.add(new Circle((int)Math.floor(Math.random()*(365-35)+1)+35,(int)Math.floor(Math.random()*(345-55)+1)+55));
                                        PlayingField.this.repaint();
                                    }
                                    playing = true;
                                    timer.start();
                                }
                            }
                        }
                    }
                    // check if inside JPanel
                } else if(e.getX() < 366 && e.getX() > 34 && e.getY() > 34 && e.getY() < 346){
                    // # circles < difficulty, add and draw
                    circles.add(new Circle(e.getX()-35, e.getY()-35));
                    PlayingField.this.repaint();
                    difficultyNumCircles++;
                    // start round 1 if num of circles = difficulty
                    if(difficultyNumCircles % numCircles == 0){
                        JOptionPane.showMessageDialog(null, "Are you ready to start round " + rounds + "?", "Round " + rounds, JOptionPane.INFORMATION_MESSAGE);
                        playing = true;
                        timer.start();
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

    // set Panel size
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    // Timer function; repeats every 1000 milliseconds
    public void actionPerformed(ActionEvent e){
        if(playing){
            // increase reposition variables by 18 every round
            if(rounds > 1){
                upperIntX = upperIntX + ((rounds - 1) * 18);
                lowerIntX = lowerIntX + ((rounds - 1) * 18);
                upperIntY = upperIntY + ((rounds - 1) * 18);
                lowerIntY = lowerIntY + ((rounds - 1) * 18);
            }
            for(Circle circle: circles){
                // make sure circles stay inside panel
                if(circle.getOriginalX() + upperIntX > 365){
                    upperIntX = 365 - circle.getOriginalX();
                } else if(circle.getOriginalX() - lowerIntX < 35){
                    lowerIntX = circle.getOriginalX() - 35;
                } else if(circle.getOriginalY() + upperIntY > 345){
                    upperIntY = 345 - circle.getOriginalY();
                } else if(circle.getOriginalY() - lowerIntY < 55){
                    lowerIntY = circle.getOriginalY() - 35;
                }
                // get random # for x and y
                int newX = (int) Math.floor(Math.random()*((circle.getOriginalX()+upperIntX)-(circle.getOriginalX()-lowerIntX))+(circle.getOriginalX()-lowerIntX)+1);
                int newY = (int)Math.floor(Math.random()*((circle.getOriginalY()+upperIntY)-(circle.getOriginalY()-lowerIntY))+(circle.getOriginalY()-lowerIntY)+1);
                // set random x and y position every second
                circle.setX(newX);
                circle.setY(newY);
                PlayingField.this.repaint();
            }
            roundTime--;
            if(roundTime == 0){
                JOptionPane.showMessageDialog(null, "Uh Oh! You ran out of time! Go back to the menu and click restart to begin a new game!", "Game Over!!", JOptionPane.INFORMATION_MESSAGE);
                rounds = 1;
                roundTime = 15;
                playing = false;
                circles.clear();
                PlayingField.this.repaint();
            }
        }
    }

    // paint circles on Panel
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Circle circle: circles){
            circle.drawCircle(g);
        }
    }
}