package GUIV2;

import Database.SqlConnection;
import GUI.Listeners.ComboboxListener;
import GUIV2.Listeners.Layout1Listener;
import GUIV2.Listeners.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserInterface {
    private JFrame frame;
    private Container container;
    private JTextArea textArea;
    private SqlConnection connection;

    public UserInterface(SqlConnection connection) {
        this.connection = connection;
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
        overzicht2.addActionListener(new GUIV2.Listeners.MenuListener(2, this));
        overzicht3.addActionListener(new GUIV2.Listeners.MenuListener(3, this));
        overzicht4.addActionListener(new GUIV2.Listeners.MenuListener(4, this));
        overzicht5.addActionListener(new GUIV2.Listeners.MenuListener(5, this));
        overzicht6.addActionListener(new GUIV2.Listeners.MenuListener(6, this));

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

    public void setPanelLayout1() {

        JPanel panel = new JPanel(new BorderLayout());
        this.textArea = new JTextArea(" ");
        JScrollBar scrollBar = new JScrollBar();

        this.textArea.setEditable(false);

        //Jcombobox opmaken
        String list = "";
        try {
            ResultSet rs = this.connection.executeSql("SELECT Serie.Title FROM Serie;");
            while (rs.next()) {
                list += rs.getString("Title") + "," ;
            }

            if (list != null && list.length() > 0 && list.charAt(list.length() - 1) == ',') {
                list = list.substring(0, list.length() - 1);
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        JComboBox jcb = new JComboBox(list.split(","));

        jcb.addActionListener(new Layout1Listener(this, this.connection));

        jcb.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        textArea.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));

        //panelen toevoegen
        panel.add(jcb, BorderLayout.NORTH);
        panel.add(this.textArea, BorderLayout.CENTER);
        panel.add(scrollBar, BorderLayout.EAST);

        this.container.add(panel, BorderLayout.CENTER);
    }

    public void changeLayout1(String text) {
        this.textArea.setText(text);
    }
}
