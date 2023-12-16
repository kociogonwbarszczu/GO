import javax.swing.*;

public class FirstFrame extends JFrame {

    FirstFrame() {
        setSize(700, 600);
        setTitle("GO");
        JButton buttonNewGame = new JButton("NEW GAME");
        JButton buttonLoadGame = new JButton("LOAD GAME");
        add(buttonNewGame);
        add(buttonLoadGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
