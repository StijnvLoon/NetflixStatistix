package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//Wanneer door de gebruiker in de GUI met layout6 een item in de combobox wordt geselecteerd, wordt er vanaf deze class een String gereturned
//naar de changeLayout() method in UserInterface.

public class Layout6Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;

    public Layout6Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

    //Deze method haalt het geselecteerde item uit de combobox op, en zet die om naar een String
    //Ook wordt het tekstveld aangepast, aan de hand van het geselecteerde item.
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String chosenFilm = (String)cb.getSelectedItem();

        ui.changeLayout(getInfo(chosenFilm));
    }

    //In deze method wordt alle informatie opgevraagt die hoort bij de gekozen film en als één grote String gereturned.
    public String getInfo(String chosenFilm) {

        int viewCount = 0;

        try {
            ResultSet rs = connection.executeSql("SELECT COUNT(Profile.Name) AS WatchedAmount\n" +
                                                        "FROM Profile JOIN Profile_Program ON Profile.ProfileName = Profile_Program.ProfileName\n" +
                                                        "JOIN Program ON Program.Id = Profile_Program.Id\n" +
                                                        "WHERE Program.Title = '"+ chosenFilm + "';");
            while (rs.next()) {
                viewCount = rs.getInt("WatchedAmount");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return "\n" + viewCount + " gebruikers hebben de film\n'" + chosenFilm + "' bekeken";


    }
}
