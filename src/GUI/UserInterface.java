package GUI;

import GUI.Layouts.FirstLayout;
import GUI.Listeners.MenuListener;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private JFrame frame;
    private Container container;

    public UserInterface() {
    }

    public void start() {
        this.frame = new JFrame("Netflix Statistics");

        this.container = this.frame.getContentPane();

        this.frame.setPreferredSize(new Dimension(800, 700));

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents();

        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void createComponents() {

        this.container.setLayout(new BorderLayout());

        this.container.add(createMenu(), BorderLayout.WEST);

    }

    public void update() {
        this.frame.pack();
    }

    private JPanel createMenu() {

        JPanel menu = new JPanel(new GridLayout(6,1));

        JButton overzicht1 = new JButton("Gemiddeld bekenen % per aflevering");
        JButton overzicht2 = new JButton("Gemiddeld bekenen % per aflevering + account");
        JButton overzicht3 = new JButton("Overzicht 3");
        JButton overzicht4 = new JButton("Overzicht 4");
        JButton overzicht5 = new JButton("Overzicht 5");
        JButton overzicht6 = new JButton("Overzicht 6");

        overzicht1.addActionListener(new MenuListener(1, this));
        overzicht2.addActionListener(new MenuListener(2, this));
        overzicht3.addActionListener(new MenuListener(3, this));
        overzicht4.addActionListener(new MenuListener(4, this));
        overzicht5.addActionListener(new MenuListener(5, this));
        overzicht6.addActionListener(new MenuListener(6, this));

        menu.add(overzicht1);
        menu.add(overzicht2);
        menu.add(overzicht3);
        menu.add(overzicht4);
        menu.add(overzicht5);
        menu.add(overzicht6);

        return menu;
    }

    public void setPanel1(UserInterface ui) {
        FirstLayout fl = new FirstLayout(ui);

        this.container.add(fl.createPanel(), BorderLayout.CENTER);
    }
}
