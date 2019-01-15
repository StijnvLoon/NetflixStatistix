package GUI;

import Database.SqlConnection;
import GUI.Listeners.Layout1Listener;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class UserInterface {
    private JFrame frame;
    private Container container;
    private JTextArea textArea1;
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

        this.container.add(createDescription(), BorderLayout.SOUTH);

        this.container.add(createMenu(), BorderLayout.WEST);

    }

    public void update() {
        this.frame.pack();
    }

    private JPanel createDescription() {
        JPanel description = new JPanel(new GridLayout(1,2));
        JTextArea name = new JTextArea("Netflix Statistix");
        JTextArea information = new JTextArea("     Informatica 2019 | Klas: b | door: Mark Govers & Stijn van Loon");

        name.setEditable(false);
        information.setEditable(false);

        name.setFont(new Font("Arial", Font.ITALIC, 13));
        information.setFont(new Font("Arial", Font.ITALIC, 13));

        description.add(name);
        description.add(information);

        return description;
    }

    private JPanel createMenu() {

        JPanel menu = new JPanel(new GridLayout(6, 1));

        JButton overzicht1 = new JButton("Afleveringen");
        JButton overzicht2 = new JButton("Afleveringen met account");
        JButton overzicht3 = new JButton("Bekeken films");
        JButton overzicht4 = new JButton("Langste film");
        JButton overzicht5 = new JButton("Accounts met 1 profiel");
        JButton overzicht6 = new JButton("Aantal kijkers per film");

        overzicht1.addActionListener(new GUI.Listeners.MenuListener(1, this));
        overzicht2.addActionListener(new GUI.Listeners.MenuListener(2, this));
        overzicht3.addActionListener(new GUI.Listeners.MenuListener(3, this));
        overzicht4.addActionListener(new GUI.Listeners.MenuListener(4, this));
        overzicht5.addActionListener(new GUI.Listeners.MenuListener(5, this));
        overzicht6.addActionListener(new GUI.Listeners.MenuListener(6, this));

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
        this.textArea1 = new JTextArea(" ");

        this.textArea1.setEditable(false);

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
        textArea1.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));

        //panelen toevoegen
        panel.add(jcb, BorderLayout.NORTH);
        panel.add(this.textArea1, BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }

    public void changeLayout1(String text) {
        this.textArea1.setText(text);
    }
}
