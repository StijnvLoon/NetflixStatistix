package GUI.Listeners;

import Database.SqlConnection;
import GUI.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Layout4Listener implements ActionListener {

    private UserInterface ui;
    private SqlConnection connection;

    public Layout4Listener(UserInterface ui, SqlConnection connection) {
        this.ui = ui;
        this.connection = connection;
    }

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
