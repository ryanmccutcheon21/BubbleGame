import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class PlayingField extends JPanel implements ActionListener{
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
    private int roundTime = 15000;
    // random x and y variables
    private int newX, newY;

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
                        if(new FindDistance(circle.getX(), circle.getY(), e.getX()-35, e.getY()-35).getDistance() < 35){
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
                                    roundTime = 16000 - (rounds * 1000);
                                    for(int i = 0; i < difficultyNumCircles; i++){
                                        // random positions for circle starting position rounds 2-10
                                        circles.add(new Circle((int) Math.floor((Math.random() * 330) + 1),(int) Math.floor((Math.random() * 310) + 1), rounds));
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
                    circles.add(new Circle(e.getX()-35, e.getY()-35, rounds));
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

    // set Panel size
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    // Timer function; repeats every 250 milliseconds
    public void actionPerformed(ActionEvent e){
        if(playing){
            for(Circle circle: circles){
                newX = circle.getRandomX();
                newY = circle.getRandomY();
                // loop through circles arraylist to check if touching
                for(int i = 0; i < circles.size(); i++){
                    int x = circles.get(i).getX()-35;
                    int y = circles.get(i).getY()-35;
                    // if circles touch, get new coordinates
                    // and check again
                    if(new FindDistance(newX-35, newY-35, x, y).getDistance() < 70 && i != circles.indexOf(circle)){
                        newX = circle.getRandomX();
                        newY = circle.getRandomY();
                        i = 0;
                    }
                }
                // // set random x and y position every second
                circle.setX(newX);
                circle.setY(newY);
                PlayingField.this.repaint();
            }
            roundTime-=1000;
            if(roundTime <= 0){
                JOptionPane.showMessageDialog(null, "Out of time! Go back to the menu and click restart to begin a new game!", "Game Over!!", JOptionPane.INFORMATION_MESSAGE);
                rounds = 1;
                roundTime = 15000;
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