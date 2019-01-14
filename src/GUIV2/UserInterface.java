package GUIV2;

import GUIV2.Listeners.MenuListener;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private JFrame frame;
    private Container container;
    private JTextArea textArea;

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

        JPanel menu = new JPanel(new GridLayout(6, 1));

        JButton overzicht1 = new JButton("Gemiddeld bekenen % per aflevering");
        JButton overzicht2 = new JButton("Gemiddeld bekenen % per aflevering + account");
        JButton overzicht3 = new JButton("Overzicht 3");
        JButton overzicht4 = new JButton("Overzicht 4");
        JButton overzicht5 = new JButton("Overzicht 5");
        JButton overzicht6 = new JButton("Overzicht 6");

        overzicht1.addActionListener(new GUIV2.Listeners.MenuListener(1, this));
        overzicht2.addActionListener(new MenuListener(2, this));
        overzicht3.addActionListener(new MenuListener(3, this));
        overzicht4.addActionListener(new MenuListener(4, this));
        overzicht5.addActionListener(new MenuListener(5, this));
        overzicht6.addActionListener(new MenuListener(6, this));

        overzicht1.setFont(new Font("Arial", Font.BOLD, 14));
        overzicht2.setFont(new Font("Arial", Font.BOLD, 14));
        overzicht3.setFont(new Font("Arial", Font.BOLD, 14));
        overzicht4.setFont(new Font("Arial", Font.BOLD, 14));
        overzicht5.setFont(new Font("Arial", Font.BOLD, 14));
        overzicht6.setFont(new Font("Arial", Font.BOLD, 14));

        menu.add(overzicht1);
        menu.add(overzicht2);
        menu.add(overzicht3);
        menu.add(overzicht4);
        menu.add(overzicht5);
        menu.add(overzicht6);

        return menu;
    }

    public void setPanelLayout1(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        this.textArea = new JTextArea(text);

        this.textArea.setEditable(false);

        String[] filmList = {"Pirates of the Caribbean: The Curse of the Black Pearl", "Lord of the rings", "Die hard", "The hobbit", "Harry Potter"};
        JComboBox jcb = new JComboBox(filmList);

        jcb.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        textArea.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));

        panel.add(jcb, BorderLayout.NORTH);
        panel.add(this.textArea, BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }
}
