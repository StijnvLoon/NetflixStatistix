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

        BorderLayout layout = new BorderLayout();

        container.setLayout(layout);

        container.add(createMenu(), BorderLayout.WEST);

    }

    private JPanel createMenu() {

        JButton overzicht1 = new JButton("Overzicht 1");
        JButton overzicht2 = new JButton("Overzicht 2");
        JButton overzicht3 = new JButton("Overzicht 3");
        JButton overzicht4 = new JButton("Overzicht 4");
        JButton overzicht5 = new JButton("Overzicht 5");

        overzicht1.addActionListener(new MenuListener(1));
        overzicht2.addActionListener(new MenuListener(2));
        overzicht3.addActionListener(new MenuListener(3));
        overzicht4.addActionListener(new MenuListener(4));
        overzicht5.addActionListener(new MenuListener(5));

        JPanel menu = new JPanel(new GridLayout(5,1));

        menu.add(overzicht1);
        menu.add(overzicht2);
        menu.add(overzicht3);
        menu.add(overzicht4);
        menu.add(overzicht5);

        return menu;
    }
}
