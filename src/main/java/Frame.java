import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public void firstFrame() {
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        setSize(550, 300);
        setTitle("GO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
