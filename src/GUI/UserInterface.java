package GUI;

import Database.SqlConnection;
import GUI.Listeners.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

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

        this.frame.setPreferredSize(new Dimension(800, 1040));

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
        JButton overzicht3 = new JButton("Bekeken films door accounts");
        JButton overzicht4 = new JButton("<html>Zoek een film voor <br />onder de 16 jaar</html>");
        JButton overzicht5 = new JButton("Accounts met x profiel(en)");
        JButton overzicht6 = new JButton("Aantal kijkers per film");

        overzicht1.addActionListener(new GUI.Listeners.MenuListener(1, this));
        overzicht2.addActionListener(new GUI.Listeners.MenuListener(2, this));
        overzicht3.addActionListener(new GUI.Listeners.MenuListener(3, this));
        overzicht4.addActionListener(new GUI.Listeners.MenuListener(4, this));
        overzicht5.addActionListener(new GUI.Listeners.MenuListener(5, this));
        overzicht6.addActionListener(new GUI.Listeners.MenuListener(6, this));

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        overzicht1.setFont(buttonFont);
        overzicht2.setFont(buttonFont);
        overzicht3.setFont(buttonFont);
        overzicht4.setFont(buttonFont);
        overzicht5.setFont(buttonFont);
        overzicht6.setFont(buttonFont);

        menu.add(overzicht1);
        menu.add(overzicht2);
        menu.add(overzicht3);
        menu.add(overzicht4);
        menu.add(overzicht5);
        menu.add(overzicht6);

        return menu;
    }

    public void resetContainer() {
        try {
            this.container.remove(2);
        } catch (Exception e) {
        }
    }

    public JTextArea createJtextArea() {
        this.textArea = new JTextArea("");

        this.textArea.setEditable(false);
        this.textArea.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        this.textArea.setWrapStyleWord(true);

        return this.textArea;
    }

    public JComboBox createJcomboboxSeries() {
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

        jcb.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));

        return jcb;
    }

    public JComboBox createJcomboboxAccounts() {
        String list = "";
        try {
            ResultSet rs = this.connection.executeSql("SELECT Subscription.Name FROM Subscription;");
            while (rs.next()) {
                list += rs.getString("Name") + "," ;
            }
            if (list != null && list.length() > 0 && list.charAt(list.length() - 1) == ',') {
                list = list.substring(0, list.length() - 1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        JComboBox jcb = new JComboBox(list.split(","));

        jcb.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));

        return jcb;
    }

    public JComboBox createJcomboboxFilms() {
        String list = "";
        try {
            ResultSet rs = this.connection.executeSql("SELECT Program.Title FROM Film JOIN Program on Program.Id = Film.Id;");
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

        jcb.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));

        return jcb;
    }

    public void setPanelLayout1() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelNorth = new JPanel(new GridLayout(2,1));

        JTextArea textNorth = createJtextArea();
        textNorth.setText("Kies een aflevering:");

        JComboBox jcbSeries = createJcomboboxSeries();
        jcbSeries.addActionListener(new Layout1Listener(this, this.connection));

        panelNorth.add(textNorth);
        panelNorth.add(jcbSeries);

        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(createJtextArea(), BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }

    public void changeLayout(String text) {
        this.textArea.setText(text);
    }

    public void setPanelLayout2() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelNorth = new JPanel(new GridLayout(2,2));

        JTextArea account = createJtextArea();
        JTextArea serie = createJtextArea();

        account.setText("Selecteer een account:");
        serie.setText("Kies een serie:");

        JComboBox jcbaccounts = createJcomboboxAccounts();
        JComboBox jcbseries = createJcomboboxSeries();

        jcbaccounts.setName("accounts");
        jcbseries.setName("series");

        Layout2Listener listener = new Layout2Listener(this, this.connection);
        jcbaccounts.addActionListener(listener);
        jcbseries.addActionListener(listener);

        panelNorth.add(account);
        panelNorth.add(serie);
        panelNorth.add(jcbaccounts);
        panelNorth.add(jcbseries);

        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(createJtextArea(), BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }

    public void setPanelLayout3() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelNorth = new JPanel(new GridLayout(2,1));
        JComboBox filmsjcb = createJcomboboxAccounts();

        JTextArea textNorth = createJtextArea();
        textNorth.setText("Selecteer een account:");
        panelNorth.add(textNorth);
        panelNorth.add(filmsjcb);

        filmsjcb.addActionListener(new Layout3Listener(this, this.connection));

        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(createJtextArea(), BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }

    public void setPanelLayout4() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelNorth = new JPanel(new GridLayout(2,1));
        JTextArea textNorth = createJtextArea();
        textNorth.setText("Maak een keuze:");

        String[] list = {"Zoek de kortste film voor kinderen onder de 16 jaar.", "Zoek de langste film voor kinderen onder de 16 jaar."};
        JComboBox choicejcb = new JComboBox(list);

        choicejcb.addActionListener(new Layout4Listener(this, this.connection));
        choicejcb.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));

        panelNorth.add(textNorth);
        panelNorth.add(choicejcb);

        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(createJtextArea(), BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }

    public void setPanelLayout5() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelNorth = new JPanel(new GridLayout(2,0));
        JPanel panelNorthNorth = new JPanel(new GridLayout(1,3));
        JPanel panelNotNorth = new JPanel();

        JTextArea first = createJtextArea();
        first.setText("Geef de account(s) met: ");
        JTextArea second = new JTextArea("vul een nummer in");
        JTextArea third = createJtextArea();
        third.setText("profiel(en)");

        JButton searchButton = new JButton("Zoek!");

        second.setFont(new Font("Arial", Font.BOLD, 16));
        second.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));

        searchButton.addActionListener(new Layout5Listener(this, this.connection, second));

        panelNorthNorth.add(first);
        panelNorthNorth.add(second);
        panelNorthNorth.add(third);
        panelNotNorth.add(searchButton);

        panelNorth.add(panelNorthNorth);
        panelNorth.add(panelNotNorth);

        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(createJtextArea(), BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);

    }
}
