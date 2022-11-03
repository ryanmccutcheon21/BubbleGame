import javax.swing.*;
import java.awt.event.*;

// First page
class Panel implements ActionListener {

    JButton startButton, restartButton;
    JFrame frame;
    JSlider slide;
    JLabel label;

    public Panel() {
        // Frame
        frame = new JFrame("Final Project");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // components
        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        restartButton = new JButton("Restart");
        label = new JLabel("Make a selection for Easy, Medium, or Hard");
        slide = new JSlider(JSlider.HORIZONTAL, 4, 6, 5);

        // add to panel
        panel.add(startButton);
        panel.add(restartButton);
        panel.add(label);
        panel.add(slide);

        // action listeners
        startButton.addActionListener(this);
        restartButton.addActionListener(this);

        // add panel to frame
        frame.add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            frame = new JFrame("Playing Field");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(new PlayingField(slide.getValue()));
            JOptionPane.showMessageDialog(null, "Click anywhere to draw the circles' starting position.", "Instruction",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == restartButton) {
            frame = new JFrame("Playing Field");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(new PlayingField(slide.getValue()));
        }
    }
}
