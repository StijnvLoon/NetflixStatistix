package GUI;

import Database.SqlConnection;
import GUI.Listeners.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

//In de UserInterface worden alle componenten aangemaakt en ingezet in de verschillende menu's die ook vanaf hier worden aangemaakt.
//De acties die worden uitgevoerd door de componenten, worden behandeld en gereturned door de Listeners

public class UserInterface {
    private JFrame frame;
    private Container container;
    private JTextArea textArea;
    private SqlConnection connection;

    public UserInterface(SqlConnection connection) {
        this.connection = connection;
    }

    //start de GUI
    public void start() {
        this.frame = new JFrame("Netflix Statistics");

        this.container = this.frame.getContentPane();

        this.frame.setPreferredSize(new Dimension(800, 1035));
        this.frame.setResizable(false);

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents();

        this.frame.pack();
        this.frame.setVisible(true);
    }

    //Hiermee worden de standaard onderdelen, die niet veranderen (het menu en de descriptie balk onderaan) in de GUI gezet.
    private void createComponents() {

        this.container.setLayout(new BorderLayout());

        this.container.add(createDescription(), BorderLayout.SOUTH);

        this.container.add(createMenu(), BorderLayout.WEST);

    }

    //Na elke verandering die plaatsvind, moet het frame geupdate worden, op deze manier kan hij op elk moment geupdate worden zonder al te veel herhaling
    public void update() {
        this.frame.pack();
    }

    //Hiermee wordt de Description balk die je onderaan het programma ziet aangemaakt.
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

    //Hiermee worden het menu en zijn knoppen aangemaakt
    private JPanel createMenu() {

        JPanel menu = new JPanel(new GridLayout(6, 1));

        JButton button1 = new JButton("Afleveringen");
        JButton button2 = new JButton("Afleveringen met account");
        JButton button3 = new JButton("Bekeken films door accounts");
        JButton button4 = new JButton("<html>Zoek een film voor <br />onder de 16 jaar</html>");
        JButton button5 = new JButton("Accounts met x profiel(en)");
        JButton button6 = new JButton("Aantal kijkers per film");

        button1.addActionListener(new GUI.Listeners.MenuListener(1, this));
        button2.addActionListener(new GUI.Listeners.MenuListener(2, this));
        button3.addActionListener(new GUI.Listeners.MenuListener(3, this));
        button4.addActionListener(new GUI.Listeners.MenuListener(4, this));
        button5.addActionListener(new GUI.Listeners.MenuListener(5, this));
        button6.addActionListener(new GUI.Listeners.MenuListener(6, this));

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);
        button4.setFont(buttonFont);
        button5.setFont(buttonFont);
        button6.setFont(buttonFont);

        menu.add(button1);
        menu.add(button2);
        menu.add(button3);
        menu.add(button4);
        menu.add(button5);
        menu.add(button6);

        return menu;
    }

    //Deze method was nodig omdat tekstvelden anders over elkaar heen werden gezet. Er wordt geen exception weergegeven als er een plaatsvind,
    //omdat wij weten dat wanneer een tekstveld voor het eerst wordt gemaakt, de exception zal plaatsvinden.
    public void resetContainer() {
        try {
            this.container.remove(2);
        } catch (Exception e) {
        }
    }

    //Voor de meeste tekstvelden die worden aangemaakt gebruiken we deze method, het werd zo wat overzichtelijker en herhalen wordt voorkomen.
    public JTextArea createJtextArea() {
        this.textArea = new JTextArea("");

        this.textArea.setEditable(false);
        this.textArea.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        this.textArea.setWrapStyleWord(true);

        return this.textArea;
    }

    //Niet elke JCombobox wordt meerdere keren gebruikt, maar het opzetten van panelen word doormiddel van deze methods een stuk overzichtelijker.
    //In deze method wordt de JCombobox, ook wel dropdownmenu, aangemaakt die alle 'series' die hij kan vinden in de database laat zien.
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

    //Hetzelfde als bij createJcomboboxSeries, maar dan met 'Accounts'
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

    //Hetzelfde als bij createJcomboboxSeries, maar dan met 'Films'
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

    //Er is één textArea, die telkens aangepast wordt met deze method. Hij wordt aangeroepen door de Listeners.
    public void changeLayout(String text) {
        this.textArea.setText(text);
    }

    //Wanneer er op button1 in de menu wordt geklikt, wordt deze layout weergegeven.
    //De onderdelen van de layout zijn gebonden aan Layout1Listener.
    public void setPanelLayout1() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelNorth = new JPanel(new GridLayout(2,1));

        JTextArea textNorth = createJtextArea();
        textNorth.setText("Kies een serie en achterhaal zijn afleveringen:");

        JComboBox jcbSeries = createJcomboboxSeries();
        jcbSeries.addActionListener(new Layout1Listener(this, this.connection));

        panelNorth.add(textNorth);
        panelNorth.add(jcbSeries);

        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(createJtextArea(), BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }

    //Wanneer er op button2 in de menu wordt geklikt, wordt deze layout weergegeven.
    //De onderdelen van de layout zijn verbonden aan Layout2Listener.
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

    //Wanneer er op button3 in de menu wordt geklikt, wordt deze layout weergegeven.
    //De onderdelen van de layout zijn verbonden aan Layout3Listener.
    public void setPanelLayout3() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelNorth = new JPanel(new GridLayout(2,1));
        JComboBox filmsjcb = createJcomboboxAccounts();

        JTextArea textNorth = createJtextArea();
        textNorth.setText("Selecteer een account om de bekeken films ervan te achterhalen:");
        panelNorth.add(textNorth);
        panelNorth.add(filmsjcb);

        filmsjcb.addActionListener(new Layout3Listener(this, this.connection));

        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(createJtextArea(), BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }

    //Wanneer er op button4 in de menu wordt geklikt, wordt deze layout weergegeven.
    //De onderdelen van de layout zijn verbonden aan Layout4Listener.
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

    //Wanneer er op button5 in de menu wordt geklikt, wordt deze layout weergegeven.
    //De onderdelen van de layout zijn verbonden aan Layout5Listener.
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

        panelNotNorth.setBackground(Color.WHITE);

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

    //Wanneer er op button6 in de menu wordt geklikt, wordt deze layout weergegeven.
    //De onderdelen van de layout zijn verbonden aan Layout6Listener.
    public void setPanelLayout6() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelNorth = new JPanel(new GridLayout(2,1));
        JTextArea textNorth = createJtextArea();
        textNorth.setText("Selecteer een film:");

        JComboBox jcbFilms = createJcomboboxFilms();
        jcbFilms.addActionListener(new Layout6Listener(this, this.connection));

        panelNorth.add(textNorth);
        panelNorth.add(jcbFilms);

        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(createJtextArea(), BorderLayout.CENTER);

        this.container.add(panel, BorderLayout.CENTER);
    }
}
