package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//Wanneer door de gebruiker in de GUI met layout4 een item in de combobox wordt geselecteerd, wordt er vanaf deze class een String gereturned
//naar de changeLayout() method in UserInterface.

public class Layout4Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;

    public Layout4Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

    //Deze method haalt het geselecteerde item uit de combobox op, en aan de hand van het item wordt de opgehaalde informatie d.m.v. getInfo op
    //volgorde van groot naar klein of andersom gesorteerd.
    //Ook wordt het tekstveld aangepast, aan de hand van het geselecteerde item.
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String chosenOption = (String)cb.getSelectedItem();

        if (chosenOption.equals("Zoek de kortste film voor kinderen onder de 16 jaar.")) {
            chosenOption = "ASC";
        } else {
            chosenOption = "DESC";
        }

        ui.changeLayout(getInfo(chosenOption));
    }

    //In deze method wordt alle informatie die horen bij de gekozen serie en als één grote String gereturned.
    //Er is hier geen ArrayList nodig omdat er maar om 1 ding wordt gevraagd.
    public String getInfo(String chosenOption) {

        String film = "";

        try {
            ResultSet rs = connection.executeSql("SELECT TOP 1 Program.Title\n" +
                                                "FROM Program JOIN Film ON Film.Id = Program.Id\n" +
                                                "JOIN Profile_Program ON Program.Id = Profile_Program.Id\n" +
                                                "JOIN Profile ON Profile.ProfileName = Profile_Program.ProfileName\n" +
                                                "WHERE Film.AgeRestriction <= 16\n" +
                                                "ORDER BY Duration " + chosenOption + ";");
            while (rs.next()) {
                film = rs.getString("Title");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return "\n" + film;
    }
}
