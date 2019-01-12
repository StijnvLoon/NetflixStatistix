package GUI;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private JFrame frame;

    public UserInterface() {
    }

    public void start() {
        frame = new JFrame("Netflix Statistics");
        frame.setPreferredSize(new Dimension(800, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
    }
}
