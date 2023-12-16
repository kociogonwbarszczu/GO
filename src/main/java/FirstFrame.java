import javax.swing.*;
import java.awt.*;

public class FirstFrame extends JFrame {
    FirstFrame() {
        //size
        setSize(700, 600);

        //title
        setTitle("GO");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //label
        JLabel titleLabel = new JLabel("GO");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //buttons
        JButton buttonNewGame = new JButton("NEW GAME");
        buttonNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buttonLoadGame = new JButton("LOAD GAME");
        buttonLoadGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonNewGame);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonLoadGame);
        panel.add(Box.createVerticalGlue());

        // Center the panel on the frame
        setLayout(new BorderLayout());
        add(Box.createHorizontalGlue(), BorderLayout.WEST);
        add(panel, BorderLayout.CENTER);
        add(Box.createHorizontalGlue(), BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
}
